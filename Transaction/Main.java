package Transaction;

import java.util.*;

public class Main {
    static Map<Character, Integer> permanentData = new HashMap<>();
    static Deque<Map<Character, Integer>> transactionStack = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1)SET 2)GET 3)UNSET 4)COUNT 5)BEGIN 6)ROLLBACK 7)COMMIT 8)UPDATE 9)EXIT");
            System.out.println("Enter the Option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the Variable: ");
                    char ch = sc.next().charAt(0);
                    System.out.println("Enter the value: ");
                    int val = sc.nextInt();
                    set(ch, val);
                    break;
                case 2:
                    System.out.println("Enter the Variable: ");
                    char variable = sc.next().charAt(0);
                    get(variable);
                    break;
                case 3:
                    System.out.println("Enter the Variable: ");
                    char var = sc.next().charAt(0);
                    unset(var);
                    break;
                case 4:
                    System.out.println("Enter the value: ");
                    int num = sc.nextInt();
                    count(num);
                    break;
                case 5:
                    begin();
                    break;
                case 6:
                    rollback();
                    break;
                case 7:
                    commit();
                    break;
                case 8:
                    System.out.println("Enter the Variable: ");
                    char c = sc.next().charAt(0);
                    System.out.println("Enter the value: ");
                    int nums = sc.nextInt();
                    update(c, nums);
                    break;
                case 9:
                    return;
            }
        }
    }

    private static void set(char ch,int num)
    {
        if(!transactionStack.isEmpty())
        {
            transactionStack.peek().put(ch,num);
        }
        else {
            permanentData.put(ch,num);
        }
    }

    private static void get(char ch)
    {
        if(!transactionStack.isEmpty())
        {
            if(transactionStack.peek().containsKey(ch))
            {
                System.out.println(transactionStack.peek().get(ch));
            }
            else if(permanentData.containsKey(ch))
            {
                System.out.println(permanentData.get(ch));
            }
            else{
                System.out.println("null");
            }
        }
        else {
            if(permanentData.containsKey(ch))
            {
                System.out.println(permanentData.get(ch));
            }
            else {
                System.out.println("null");
            }
        }
    }

    private static void unset(char ch)
    {
        if(!transactionStack.isEmpty())
        {
            if(transactionStack.peek().containsKey(ch))
            {
                transactionStack.peek().remove(ch);
            }
            else{
                System.out.println("No Variable Found");
            }
        }
        else {
            if(permanentData.containsKey(ch))
            {
                permanentData.remove(ch);
            }
            else {
                System.out.println("No Variable Found");
            }
        }
    }

    private static void count(int num)
    {
        int count = 0;
        if(!transactionStack.isEmpty())
        {
            for(int nums : transactionStack.peek().values())
            {
                if(nums==num)
                {
                    count++;
                }
            }
        }
        else {
            for(int nums : permanentData.values())
            {
                if(nums==num) count++;
            }
        }

        if(count==0) System.out.println("null");
        else System.out.println(count);
    }

    private static void begin()
    {
        transactionStack.push(new HashMap<>());
    }

    private static void rollback()
    {
        if(!transactionStack.isEmpty())
        {
            transactionStack.pop();
        }
    }

    private static void update(char ch,int num)
    {
        if(!transactionStack.isEmpty())
        {
            if(transactionStack.peek().containsKey(ch))
            {
                transactionStack.peek().put(ch,num);
            }
            else {
                System.out.println("No Such Variable");
            }
        }
        else {
            if(permanentData.containsKey(ch))
            {
                permanentData.put(ch,num);
            }
            else {
                System.out.println("No Such Variable");
            }
        }
    }

    public static void commit()
    {
        while(!transactionStack.isEmpty())
        {
            for(Map.Entry<Character,Integer> entry : transactionStack.pop().entrySet())
            {
                permanentData.put(entry.getKey(),entry.getValue());
            }
        }
    }

}

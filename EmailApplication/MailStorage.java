package EmailApplication;

import java.io.IOException;
import java.util.Scanner;

public class MailStorage {
    public static void main(String[] args) throws IOException {

        EmailModule mail = new EmailModule();

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag)
        {
            System.out.println("1)Compose 2)Delete 3)Edit 4)Print 5)Search 6)Add Tag 7)All Mails 8)Exit");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1 :
                   mail.composeMail();
                   break;
                case 2 :
                    mail.deleteByKeyword();
                    break;
                case 3 :
                    mail.EditByKeyword();
                    break;
                case 4 :
                    mail.printMail();
                    break;
                case 5 :
                    mail.searchByKeyword();
                    break;
                case 6 :
                    mail.addTag();
                    break;
                case 7 :
                    mail.displayAllMail();
                    break;
                case 8 :
                    flag=false;
                    break;
            }

        }
    }
}

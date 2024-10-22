package SuperMarketApplication.Repository;

public class LinkedList {

    public void insertData(Node head, Object data)
    {
        Node node = new Node(data);

        if(head ==null)
        {
           return;
        }
        else {
           Node temp = head;
           while(temp.next!=null)
           {
               temp = temp.next;
           }
           temp.next = node;
        }
    }

    public void deleteData(Node prev) {

        if(prev.next!=null) {
            prev.next = prev.next.next;
        }
    }
}
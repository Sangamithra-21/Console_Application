package EmailApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmailModule {

    FileHandler handler = new FileHandler();


    Scanner sc = new Scanner(System.in);
    public void composeMail() throws IOException {

        System.out.print("From            : ");
        String fromId = sc.nextLine();
        System.out.print("To              : ");
        String toId = sc.nextLine();
        System.out.print("Subject         : ");
        String subject = sc.nextLine();
        System.out.print("Compose content : ");
        String content = sc.nextLine();


        Email mail = new Email(fromId,toId,subject,content,"null");
        handler.writeEmailToFile(mail);

    }



    public void deleteByKeyword() throws IOException {

        System.out.print("Search Keyword : ");
        String searchWord = sc.nextLine();

        handler.displayFromFile(searchWord);

        System.out.print("Enter the mail id to delete : ");
        String mailId = sc.nextLine();

        handler.deleteMailFromFile(mailId);

        System.out.println("Mail deleted successfully...!");

    }


    public void EditByKeyword() throws IOException {

        System.out.print("Search Keyword : ");
        String searchWord = sc.nextLine();

        handler.displayFromFile(searchWord);

        System.out.print("Enter the mail id to edit : ");
        String mailId = sc.nextLine();

        System.out.print("Old Word : ");
        String oldValue = sc.nextLine();

        System.out.print("New Word : ");
        String newValue = sc.nextLine();

        handler.editMail(mailId,oldValue,newValue);

        System.out.println("Mail Edited Successfully...!");


    }


    public void printMail() throws IOException {

        System.out.print("Enter the mail Id to Print : ");
        String mailId = sc.nextLine();

        handler.displayByMailId(mailId);


    }

    public void searchByKeyword() throws IOException {

        System.out.print("Enter the word to search : ");

        String searchWord = sc.nextLine();

        handler.displayFromFile(searchWord);

    }

    public void addTag() throws IOException {

        System.out.print("Enter the tag name : ");
        String tag = sc.nextLine();

        handler.displayAllMails();

        List<String> idList = new ArrayList<>();

        System.out.println("Enter the mail id's added to that tag ( '0' to quit ): ");

        String val = "";
        boolean flag=true;
        while(flag)
        {
            val = sc.next();
            if(val.equals("0"))
            {
                flag=false;
            }
            idList.add(val);
        }

        handler.addTagToFile(idList,tag);
    }

    public void displayAllMail() throws IOException {

        handler.displayImportantMail();
        handler.displayRemainingMail();
    }
}

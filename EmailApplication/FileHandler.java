package EmailApplication;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.*;

public class FileHandler {

    File originalFile = new File("EmailData.txt");

    public void writeEmailToFile(Email mail) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(originalFile, true));

        String email = mailObjectToString(mail);

        writer.write(email);

        writer.newLine();

        writer.close();


    }

    private String mailObjectToString(Email mail) {

        return mail.mailId + " | " + mail.fromId + " | " + mail.toId + " | " + mail.subject + " | " + mail.tag + " | " + mail.mailContent;
    }


    public void displayFromFile(String searchWord) throws IOException {

        Set<String> set = new HashSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        String currLine = "";

        String regexPattern = convertToRegex(searchWord);


        Pattern pattern = Pattern.compile(regexPattern);

        boolean findValue = false;

        while ((currLine = reader.readLine()) != null) {

            String[] mails = currLine.split("\\s*\\|\\s*");


            for (int i = 1; i < mails.length; i++) {

                    Matcher matcher = pattern.matcher(mails[i]);

                    if (matcher.find() && !set.contains(currLine)) {
                        System.out.println(currLine);
                        set.add(currLine);
                        findValue = true;
                        break;
                    }

            }
        }

        if (!findValue) {
            System.out.println("No such results found");
        }

        reader.close();
    }


    private String convertToRegex(String searchWord) {

        String regex = searchWord.replace("*", ".*");

        regex = regex.replace("+", ".+");

        return regex;
    }


    public void deleteMailFromFile(String mailId) throws IOException {

        File newFile = new File("tempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile,false));

        String currLine="";

        boolean findMail = false;

        while((currLine=reader.readLine())!=null){
            String[] arr = currLine.split("\\s*\\|\\s*");
            if(!arr[0].equals(mailId))
            {
                writer.write(currLine);
                writer.newLine();
                findMail = true;
            }
        }

        if(findMail==false)
        {
            System.out.println("No such Mail Exists");
        }

        reader.close();
        writer.close();

        if(originalFile.delete())
        {
            newFile.renameTo(originalFile);
        }

    }

    public void editMail(String mailId,String oldWord,String newWord) throws IOException {

        File newFile = new File("editTemp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile,false));

        String currLine ="";
        boolean findMail = true;

        while((currLine=reader.readLine())!=null)
        {
            String[] mail = currLine.split("\\s*\\|\\s*");
            if(mail[0].equals(mailId))
            {
                currLine = currLine.replaceAll(oldWord,newWord);
                findMail = false;
            }
            writer.write(currLine);
            writer.newLine();
        }

        reader.close();
        writer.close();

        if(findMail)
        {
            System.out.println("No Such Mail id Found");
        }

        if(originalFile.delete())
        {
            newFile.renameTo(originalFile);
        }

    }

    public void displayByMailId(String mailId) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        String currLine = "";


        while((currLine=reader.readLine())!=null)
        {
            String[] mail = currLine.split("\\s*\\|\\s*");
            if(mail[0].equals(mailId))
            {
                System.out.println("Mail Id   : "+mail[0]);
                System.out.println("From Id   : "+mail[1]);
                System.out.println("To Id     : "+mail[2]);
                System.out.println("Subject   : "+mail[3]);
                System.out.println("Content   : "+mail[5]);
                if(mail[4]!=null)
                {
                    System.out.println("Tag       : "+mail[4]);
                }
                return;
            }
        }
        System.out.println("No such Mail exists");

        reader.close();
    }

    public void displayAllMails() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        String currLine = "";

        while((currLine=reader.readLine())!=null)
        {
            System.out.println(currLine);
        }
        reader.close();
    }

    public void addTagToFile(List<String> idList,String tag) throws IOException {

        File newFile = new File("tag.txt");

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile,false));

        String currLine = "";

        while((currLine=reader.readLine())!=null)
        {
            String[] mail = currLine.split("\\s*\\|\\s*");
            if(idList.contains(mail[0]))
            {
                mail[4]=tag;
                currLine = String.join(" | ", mail);
            }
            writer.write(currLine);
            writer.newLine();

        }

        System.out.println("Tag Added Successfully");

        reader.close();
        writer.close();


        if(originalFile.delete())
        {
            newFile.renameTo(originalFile);
        }
    }

    public void displayImportantMail() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        String currLine = "";

        while((currLine=reader.readLine())!=null)
        {
            String[] mails = currLine.split("\\s*\\|\\s*");
            if(mails[4].equals("important"))
            {
                System.out.println(currLine);
            }
        }
        reader.close();
    }

    public void displayRemainingMail() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(originalFile));

        String currLine = "";

        while((currLine=reader.readLine())!=null)
        {
            String[] mails = currLine.split("\\s*\\|\\s*");
            if(!mails[4].equals("important"))
            {
                System.out.println(currLine);
            }
        }
        reader.close();
    }
}

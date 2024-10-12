package EmailApplication;

public class Email {
    static int id=1;
    int mailId;
    String fromId;
    String toId;
    String mailContent;
    String subject;
    String tag;

    public Email(String fromId,String toId,String subject,String mailContent,String tag)
    {
        this.fromId = fromId;
        this.toId = toId;
        this.mailContent = mailContent;
        this.tag = tag;
        this.subject = subject;
        this.mailId = id++;
    }
}

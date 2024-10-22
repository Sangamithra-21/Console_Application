package AssertManagementSystem.DTO;

import java.util.Date;

public class Installation {

    static int iid = 1;
    private int installId;
    private Software software;
    private Date installDate;

    public Installation(Software software,Date date)
    {
        this.installId = iid++;
        this.software = software;
        this.installDate = date;
    }

    public int getInstallId() {
        return installId;
    }

    public void setInstallId(int installId) {
        this.installId = installId;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }
}

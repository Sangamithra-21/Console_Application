package FoodDeliverySystem.DTO;

public class Agent extends User {

    static int aid=1;
    private int agentId;
    private int[] currLocation;
    private boolean status ;

    public Agent(String username,String password,int x,int y,boolean status)
    {
        super(username,password);
        this.agentId=aid++;
        this.currLocation = new int[]{x,y};
        this.status = status;
    }
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int[] getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(int[] currLocation) {
        this.currLocation = currLocation;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

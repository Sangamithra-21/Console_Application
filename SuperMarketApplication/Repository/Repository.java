package SuperMarketApplication.Repository;



public class Repository {

    private static Repository repository;

    public Node Buyer;
    public Node Seller;
    public Node Items;

    private Repository()
    {
        this.Buyer = null;
        this.Seller = null;
        this.Items = null;
    }

    public static Repository getInstance()
    {
        if(repository==null)
        {
            repository = new Repository();
        }
        return repository;
    }
}

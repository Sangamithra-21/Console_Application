package SuperMarketApplication.SuperMarket;

import FoodDeliverySystem.DTO.Items;
import SuperMarketApplication.DTO.Buyer;
import SuperMarketApplication.DTO.Order;
import SuperMarketApplication.DTO.Product;
import SuperMarketApplication.DTO.Seller;
import SuperMarketApplication.Repository.Node;
import SuperMarketApplication.Repository.LinkedList;
import SuperMarketApplication.Repository.Repository;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;


public class MarketViewModel {


    Repository respository = Repository.getInstance();

    LinkedList ll = new LinkedList();
    private MarketView view;

    public MarketViewModel(MarketView marketView) {
        this.view = marketView;
    }


    public void addSeller(String sellerName, String password) {

        Seller seller = new Seller(sellerName, password);

        if (respository.Seller == null) {
            respository.Seller = new Node(seller);
        } else {
            ll.insertData(respository.Seller, seller);
        }

    }

    public void addBuyer(String buyerName, String password) {

        Buyer buyer = new Buyer(buyerName, password);

        if (respository.Buyer == null) {
            respository.Buyer = new Node(buyer);
        } else {
            ll.insertData(respository.Buyer, buyer);
        }
    }

    public Seller checkIsValidSupplier(String name, String password) {

        Node temp = respository.Seller;
        while (temp != null) {
            Seller seller = (Seller) temp.data;

            if (seller.getUsername().equals(name) && seller.getPassword().equals(password)) {
                return seller;
            }

            temp = temp.next;

        }
        return null;
    }

    public Buyer checkisValidBuyer(String buyerName, String password) {

        Node temp = respository.Buyer;

        while (temp != null) {
            Buyer buyer = (Buyer) temp.data;

            if (buyer.getUsername().equals(buyerName) && buyer.getPassword().equals(password)) {
                return buyer;
            }
            temp = temp.next;
        }

        return null;
    }

    public Node getProductNode(Seller seller, String productName) {

        Node temp = seller.Products;
        while (temp != null) {
            Product product = (Product) temp.data;
            if (product.getProductName().equals(productName)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void additemsBySupplier(Node temp, Items item) {

        Product product = (Product) temp.data;

        if (product.Items == null) {
            product.Items = new Node(item);
        } else {
            ll.insertData(product.Items, item);

        }
        addItemsToRepo(item);

    }

    private void addItemsToRepo(Items item) {

        if (respository.Items == null) {
            respository.Items = new Node(item);
        } else {
            ll.insertData(respository.Items, item);
        }
    }

    public void addproductToSeller(Seller seller, Product product) {

        if (seller.Products == null) {
            seller.Products = new Node(product);
        } else {
            ll.insertData(seller.Products, product);
        }

    }

    public void addItemsToTheCart(Buyer buyer, Items item) {

        if (buyer.cart == null) {
            buyer.cart = new Node(item);
        } else {
            ll.insertData(buyer.cart, item);
        }

    }

    public void placeOrder(Buyer buyer, Order order) {

        if (buyer.orderList == null) {
            buyer.orderList = new Node(order);
        } else {
            ll.insertData(buyer.orderList, order);
        }
    }

    public Node deleteItemById(Node cart, int id) {
        Node prev = null;
        Node current = cart;
        while (current != null) {
            Items item = (Items) cart.data;
            if (item.getItemId() == id) {
                if (prev == null) {
                    cart = current.next;
                } else {
                    ll.deleteData(cart);
                }
                System.out.println("Item Deleted from cart successfully...!");
                return cart;
            }
            prev = current;
            current = current.next;

        }

        System.out.println("No such Item Available in cart");
        return cart;
    }

    public Node cancelOrder(Node orderList, int orderId, LocalTime prevTime) {

        Node prev = null;
        Node curr = orderList;

        while (curr != null) {
            Order order = (Order) orderList.data;
            Duration duration = Duration.between(prevTime, order.getTime());
            if (order.getOrderId() == orderId) {
                if (duration.toMinutes() < 5) {
                    if (prev == null) {
                        orderList = curr.next;
                    } else {
                        ll.deleteData(prev);
                    }
                } else {
                    System.out.println("Order cannot be Cancelled..!");
                    return orderList;
                }
                System.out.println("Order cancelled Successfully...!");
                return orderList;
            }
            prev = curr;
            curr = curr.next;
        }

        System.out.println("No such orders..!");
        return orderList;
    }
}

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<String> orderList = new ArrayList<String>();
    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        return this.getCurrentTime().isAfter(openingTime) && this.getCurrentTime().isBefore(closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public void addItemToOrderList(String name){
        orderList.add(name);
    }

    public int getOrderItemPrice(String item){
        int price = 0;
        Iterator<Item> iterator = menu.iterator();
        while (iterator.hasNext()){
            Item menuItem = iterator.next();
            if(menuItem.getName().equals(item)){
                price += menuItem.getPrice();
            }
        }
        return price;
    }
    
    public int getOrderValue(){
        int orderTotal = 0;
        Iterator<String> iterator = orderList.listIterator();
        while (iterator.hasNext()){
            String orderItem = iterator.next();
            orderTotal += getOrderItemPrice(orderItem);
        }
        return orderTotal;
    }
}

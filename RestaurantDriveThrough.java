import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class RestaurantMenu {
    private List<MenuItem> menuItems;

    public RestaurantMenu() {
        menuItems = new ArrayList<>();
    }

    public void addItem(String name, double price) {
        menuItems.add(new MenuItem(name, price));
    }

    public List<MenuItem> getMenu() {
        return menuItems;
    }
}

class Order {
    private MenuItem item;

    public Order(MenuItem item) {
        this.item = item;
    }

    public MenuItem getItem() {
        return item;
    }
}

public class RestaurantDriveThrough {
    public static void main(String[] args) {
        Random random = new Random();
        RestaurantMenu menu = createMenu();
        List<Order> orders = new ArrayList<>();

        System.out.println("Welcome to the Restaurant Drive-Through!");
        System.out.println("Menu:");
        List<MenuItem> items = menu.getMenu();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " - $" + items.get(i).getPrice());
        }

        for (int i = 1; i <= 20; i++) {
            int randomItemIndex = random.nextInt(items.size());
            MenuItem orderedItem = items.get(randomItemIndex);
            Order order = new Order(orderedItem);
            orders.add(order);
            System.out.println("Order " + i + ": " + orderedItem.getName() + " - $" + orderedItem.getPrice());
        }

        double totalSales = orders.stream().mapToDouble(order -> order.getItem().getPrice()).sum();
        System.out.println("\nSummary:");
        System.out.println("Total money made: $" + totalSales);

        Map<String, Integer> itemCounts = new HashMap<>();
        for (Order order : orders) {
            String itemName = order.getItem().getName();
            itemCounts.put(itemName, itemCounts.getOrDefault(itemName, 0) + 1);
        }

        for (MenuItem menuItem : items) {
            String itemName = menuItem.getName();
            int count = itemCounts.getOrDefault(itemName, 0);
            double percentage = (count / (double) orders.size()) * 100;
            System.out.println(itemName + " - Sold: " + count + " times, Percentage: " + percentage + "%");
        }
    }

    private static RestaurantMenu createMenu() {
        RestaurantMenu menu = new RestaurantMenu();
        menu.addItem("Appetizer 1", 5.99);
        menu.addItem("Appetizer 2", 6.99);
        menu.addItem("Appetizer 3", 4.99);
        menu.addItem("Appetizer 4", 7.99);
        menu.addItem("Appetizer 5", 5.49);

        menu.addItem("Entree 1", 12.99);
        menu.addItem("Entree 2", 14.99);
        menu.addItem("Entree 3", 11.99);
        menu.addItem("Entree 4", 13.49);
        menu.addItem("Entree 5", 15.99);

        menu.addItem("Dessert 1", 4.99);
        menu.addItem("Dessert 2", 6.49);
        menu.addItem("Dessert 3", 3.99);
        menu.addItem("Dessert 4", 5.99);
        menu.addItem("Dessert 5", 7.49);

        return menu;
    }
}

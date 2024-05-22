import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create some items for the menu
        Item item1 = new Item("Sweet Corn Soup", 119);
        Item item2 = new Item("Vegetable Lasagne", 269);
        List<Item> menu = Arrays.asList(item1, item2);

        // Create a RestaurantService instance
        RestaurantService restaurantService = new RestaurantService();

        // Add restaurants to the service with menu items
        Restaurant restaurant1 = restaurantService.addRestaurant("Amelie's cafe", "Some Location", LocalTime.of(9, 0),
                LocalTime.of(22, 0));
        restaurant1.addToMenu(item1.getName(), item1.getPrice());
        restaurant1.addToMenu(item2.getName(), item2.getPrice());

        Restaurant restaurant2 = restaurantService.addRestaurant("Bistro Cafe", "Another Location", LocalTime.of(10, 0),
                LocalTime.of(20, 0));
        restaurant2.addToMenu(item1.getName(), item1.getPrice());
        restaurant2.addToMenu(item2.getName(), item2.getPrice());

        // Find and print a restaurant by name
        try {
            Restaurant foundRestaurant = restaurantService.findRestaurantByName("Amelie's cafe");
            System.out.println("Found restaurant: " + foundRestaurant.getName());

            // Check if the restaurant is open
            boolean isOpen = foundRestaurant.isRestaurantOpen(LocalTime.of(10, 0));
            System.out.println("Is restaurant open? " + isOpen);

            // Print the menu of the restaurant
            List<Item> foundMenu = foundRestaurant.getMenu();
            System.out.println("Menu:");
            for (Item item : foundMenu) {
                System.out.println(item.getName() + ": " + item.getPrice());
            }
        } catch (restaurantNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Try to find a non-existing restaurant
        try {
            restaurantService.findRestaurantByName("Non Existent Cafe");
        } catch (restaurantNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

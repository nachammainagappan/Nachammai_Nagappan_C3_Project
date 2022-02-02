import java.time.LocalTime;

class Main {
    public static void main(String args[]) {
        RestaurantService service = new RestaurantService();
        Restaurant restaurant;
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Charlie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        try{
            service.findRestaurantByName("Amelie's cafe").displayDetails();
            service.findRestaurantByName("Charlie's cafe").displayDetails();
        }
        catch (restaurantNotFoundException e){
            System.out.println(e);
        }
        System.out.println("Is restaurant open? " +restaurant.isRestaurantOpen());
    }
}
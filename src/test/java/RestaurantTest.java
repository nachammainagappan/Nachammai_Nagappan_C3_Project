import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    static RestaurantService service = new RestaurantService();
    static Restaurant restaurant1, restaurant2;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeAll
    public static void setup() {
        LocalTime openingTime = LocalTime.parse("09:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant1 = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant1.addToMenu("Sweet corn soup",119);
        restaurant1.addToMenu("Vegetable lasagne", 269);
        restaurant2 = service.addRestaurant("Flora and fauna","Chennai",openingTime,closingTime);
        restaurant2.addToMenu("Spaghetti",800);
        restaurant2.addToMenu("Pizza", 500);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant2.setOpeningTime(LocalTime.now().minusHours(1));
        restaurant2.setClosingTime(LocalTime.now().plusHours(1));
        assertTrue(restaurant2.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant2.setOpeningTime(LocalTime.now().plusHours(1));
        restaurant2.setClosingTime(LocalTime.now().plusHours(10));
        assertFalse(restaurant2.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant1.getMenu().size();
        restaurant1.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant1.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
               int initialMenuSize = restaurant1.getMenu().size();
        restaurant1.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant1.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class, ()->restaurant1.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void getOrderPrice_should_return_correct_order_value() {
        String[] orderList = new String[]{"Spaghetti", "Pizza"};
        assertEquals(1300,restaurant2.getOrderPrice(orderList));
    }

}
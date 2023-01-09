import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant restaurant = new Restaurant("Amelie's cafe","Chennai", openingTime, closingTime);

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        restaurant.openingTime = LocalTime.now();
        restaurant.closingTime = LocalTime.now().plus(8, ChronoUnit.HOURS);
        boolean isRestaurantOpen = restaurant.isRestaurantOpen();
        assertTrue(isRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        boolean isRestaurantOpen = restaurant.isRestaurantOpen();
        assertFalse(isRestaurantOpen);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void addItemsToMenu(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Milk shake", 459);
    }

    public int getMenuItemsNumber(){
        return restaurant.getMenu().size();
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        addItemsToMenu();
        int initialMenuSize = getMenuItemsNumber();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1, getMenuItemsNumber());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addItemsToMenu();
        int initialMenuSize = getMenuItemsNumber();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1, getMenuItemsNumber());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addItemsToMenu();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void order_total_must_be_greater_than_0_if_number_of_items_added_are_more_than_one() {
        addItemsToMenu();
        restaurant.addItemToOrderList("Sweet corn soup");
        assertTrue(restaurant.getOrderValue() > 0);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
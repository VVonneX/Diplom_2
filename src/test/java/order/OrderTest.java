package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.Arrays;

public class OrderTest {
    private OrderSteps steps = new OrderSteps();

    @Test
    @DisplayName("Creating a order is a not authorization test")
    @Description("Checking the status code and response of the POST request when creating order is a not authorization")
    public void createNotAuthOrderTest() {
        Order order = new Order(Arrays.asList("61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa6e"));
        steps.postNotAuthCreatePositiveOrder(order);
    }

    @Test
    @DisplayName("Creating a order is a authorization test")
    @Description("Checking the status code and response of the POST request when creating order is a authorization")
    public void createAuthOrderPositiveTest() {
        Order order = new Order(Arrays.asList("61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa6e"));
        steps.postAuthCreateOrder(order);
    }

    @Test
    @DisplayName("Creating a order is a authorization without ingredients test")
    @Description("Checking the status code and response of the POST request when creating order is a authorization without ingredients")
    public void createAuthOrderWithoutIngredientsTest() {
        Order order = new Order();
        steps.postAuthCreateOrderWithoutIngredients(order);
    }

    @Test
    @DisplayName("Creating a order is a authorization in ingredients with not valid hash test")
    @Description("Checking the status code and response of the POST request when creating order is a authorization in ingredients with not valid hash")
    public void createAuthOrderNotValidHashIngredientsTest() {
        Order order = new Order(Arrays.asList("g24g34g123g13g1", "1873fg3b81bf8b1d12AS"));
        steps.postAuthCreateOrderNotValidHashIngredients(order);
    }

    @Test
    @DisplayName("Get a order is a authorization with special user test")
    @Description("Checking the status code and response of the GET request when get order is a authorization with special user")
    public void getAuthOrderSpecialUserTest() {
        steps.getAuthOrdersSpecialUser();
    }

    @Test
    @DisplayName("Get a order is a not authorization with special user test")
    @Description("Checking the status code and response of the GET request when get order is a not authorization with special user")
    public void getNotAuthOrderSpecialUserTest() {
        steps.getNotAuthOrdersSpecialUser();
    }

}

package order.post;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.Order;
import org.junit.Assert;
import org.junit.Test;
import steps.OrderSteps;

import java.util.Arrays;

public class OrderPostApiPosiriveTest {

    private OrderSteps steps = new OrderSteps();

    @Test
    @DisplayName("Creating a order is a not authorization test")
    @Description("Checking the status code and response of the POST request when creating order is a not authorization")
    public void createNotAuthOrderTest() {
        Order order = new Order(Arrays.asList("61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa6e"));
        ValidatableResponse response = steps.postNotAuthCreatePositiveOrder(order);
        response.assertThat().statusCode(200);
        String createUserFromApi = response.extract().body().asString();
        Assert.assertTrue(createUserFromApi.contains("Люминесцентный традиционный-галактический краторный бургер"));
    }

    @Test
    @DisplayName("Creating a order is a authorization test")
    @Description("Checking the status code and response of the POST request when creating order is a authorization")
    public void createAuthOrderPositiveTest() {
        Order order = new Order(Arrays.asList("61c0c5a71d1f82001bdaaa6c", "61c0c5a71d1f82001bdaaa74", "61c0c5a71d1f82001bdaaa6e"));
        ValidatableResponse response = steps.postAuthCreateOrder(order);
        response.assertThat().statusCode(200);
        String jsonResponse = response.extract().body().asString();
        Gson gson = new Gson();
        Order orderApi = gson.fromJson(jsonResponse, Order.class);
        Assert.assertEquals("Люминесцентный традиционный-галактический краторный бургер", orderApi.getName());
        Assert.assertTrue(orderApi.isSuccess());
        Assert.assertTrue(jsonResponse.contains("Misha"));
        Assert.assertTrue(jsonResponse.contains("churikov999@yandex.ru"));
    }
}

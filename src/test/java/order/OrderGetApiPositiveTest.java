package order;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import steps.OrderSteps;

public class OrderGetApiPositiveTest {

    private OrderSteps steps = new OrderSteps();

    @Test
    @DisplayName("Get a order is a authorization with special user test")
    @Description("Checking the status code and response of the GET request when get order is a authorization with special user")
    public void getAuthOrderSpecialUserTest() {
        ValidatableResponse response = steps.getAuthOrdersSpecialUser();
        response.assertThat().statusCode(200);
        String orderJsonApi = response.extract().body().asString();
        Gson gson = new Gson();
        Order orderApi = gson.fromJson(orderJsonApi, Order.class);
        Assert.assertTrue(orderApi.isSuccess());
    }
}

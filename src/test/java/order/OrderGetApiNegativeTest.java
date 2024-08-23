package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import steps.OrderSteps;

public class OrderGetApiNegativeTest {

    private OrderSteps steps = new OrderSteps();

    @Test
    @DisplayName("Get a order is a not authorization with special user test")
    @Description("Checking the status code and response of the GET request when get order is a not authorization with special user")
    public void getNotAuthOrderSpecialUserTest() {
        ValidatableResponse response = steps.getNotAuthOrdersSpecialUser();
        response.assertThat().statusCode(401);
        Order orderApi = response.extract().body().as(Order.class);
        Assert.assertEquals("You should be authorised", orderApi.getMessage());
    }
}

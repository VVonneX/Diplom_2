package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class OrderGetApiTest {

    private OrderSteps steps = new OrderSteps();

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

package steps;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import order.Order;
import order.OrderApi;
import org.junit.Assert;
import user.AuthorizationUserApi;
import user.User;

public class OrderSteps {
    private OrderApi orderApi = new OrderApi();
    private AuthorizationUserApi authorizationUserApi = new AuthorizationUserApi();

    @Step("Checking the status code and response of the POST request when creating order is a not authorization")
    public ValidatableResponse postNotAuthCreatePositiveOrder(Order order) {
        return orderApi.createNotAuthOrder(order).then();
    }

    @Step("Checking the status code and response of the POST request when creating order is a authorization")
    public ValidatableResponse postAuthCreateOrder(Order order) {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Misha");
        ValidatableResponse responseAuth = authorizationUserApi.postAuthUser(user).then();
        user = responseAuth.extract().body().as(User.class);
        String accessToken = user.getAccessToken();
        return orderApi.createAuthOrder(order, accessToken).then();
    }

    @Step("Checking the status code and response of the GET request when get order is a authorization with special user")
    public ValidatableResponse getAuthOrdersSpecialUser() {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Misha");
        ValidatableResponse responseAuth = authorizationUserApi.postAuthUser(user).then();
        responseAuth.assertThat().statusCode(200);
        user = responseAuth.extract().body().as(User.class);
        String accessToken = user.getAccessToken();
        return orderApi.getAuthOrders(accessToken).then();
    }

    @Step("Checking the status code and response of the GET request when get order is a not authorization with special user")
    public ValidatableResponse getNotAuthOrdersSpecialUser() {
        return orderApi.getNotAuthOrders().then();
    }

}

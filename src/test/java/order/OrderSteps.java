package order;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import user.AuthorizationUserApi;
import user.User;

public class OrderSteps {
    private OrderApi orderApi = new OrderApi();
    private AuthorizationUserApi authorizationUserApi = new AuthorizationUserApi();

    @Step("Checking the status code and response of the POST request when creating order is a not authorization")
    public void postNotAuthCreatePositiveOrder(Order order) {
        ValidatableResponse response = orderApi.createNotAuthOrder(order).then();
        response.assertThat().statusCode(200);
        String createUserFromApi = response.extract().body().asString();
        Assert.assertTrue(createUserFromApi.contains("Люминесцентный традиционный-галактический краторный бургер"));
    }

    @Step("Checking the status code and response of the POST request when creating order is a authorization")
    public void postAuthCreateOrder(Order order) {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Misha");

        ValidatableResponse responseAuth = authorizationUserApi.postAuthUser(user).then();
        responseAuth.assertThat().statusCode(200);
        user = responseAuth.extract().body().as(User.class);
        String accessToken = user.getAccessToken();

        ValidatableResponse response = orderApi.createAuthOrder(order, accessToken).then();
        response.assertThat().statusCode(200);
        String jsonResponse = response.extract().body().asString();
        Gson gson = new Gson();
        Order orderApi = gson.fromJson(jsonResponse, Order.class);
        Assert.assertEquals("Люминесцентный традиционный-галактический краторный бургер", orderApi.getName());
        Assert.assertTrue(orderApi.isSuccess());
        Assert.assertTrue(jsonResponse.contains("Misha"));
        Assert.assertTrue(jsonResponse.contains("churikov999@yandex.ru"));
    }

    @Step("Checking the status code and response of the POST request when creating order is a authorization without ingredients")
    public void postAuthCreateOrderWithoutIngredients(Order order) {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Misha");

        ValidatableResponse responseAuth = authorizationUserApi.postAuthUser(user).then();
        responseAuth.assertThat().statusCode(200);
        user = responseAuth.extract().body().as(User.class);
        String accessToken = user.getAccessToken();

        ValidatableResponse response = orderApi.createAuthOrder(order, accessToken).then();
        response.assertThat().statusCode(400);
        Order orderApi = response.extract().body().as(Order.class);
        Assert.assertEquals("Ingredient ids must be provided", orderApi.getMessage());
    }

    @Step("Checking the status code and response of the POST request when creating order is a authorization in ingredients with not valid hash")
    public void postAuthCreateOrderNotValidHashIngredients(Order order) {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Misha");

        ValidatableResponse responseAuth = authorizationUserApi.postAuthUser(user).then();
        responseAuth.assertThat().statusCode(200);
        user = responseAuth.extract().body().as(User.class);
        String accessToken = user.getAccessToken();

        ValidatableResponse response = orderApi.createAuthOrder(order, accessToken).then();
        response.assertThat().statusCode(500);
        String orderApi = response.extract().body().asString();
        Assert.assertTrue(orderApi.contains("<html lang=\"en\">"));
    }

    @Step("Checking the status code and response of the GET request when get order is a authorization with special user")
    public void getAuthOrdersSpecialUser() {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Misha");

        ValidatableResponse responseAuth = authorizationUserApi.postAuthUser(user).then();
        responseAuth.assertThat().statusCode(200);
        user = responseAuth.extract().body().as(User.class);
        String accessToken = user.getAccessToken();

        ValidatableResponse response = orderApi.getAuthOrders(accessToken).then();
        response.assertThat().statusCode(200);
        String orderJsonApi = response.extract().body().asString();
        Gson gson = new Gson();
        Order orderApi = gson.fromJson(orderJsonApi, Order.class);
        Assert.assertTrue(orderApi.isSuccess());
    }

    @Step("Checking the status code and response of the GET request when get order is a not authorization with special user")
    public void getNotAuthOrdersSpecialUser() {
        ValidatableResponse response = orderApi.getNotAuthOrders().then();
        response.assertThat().statusCode(401);
        Order orderApi = response.extract().body().as(Order.class);
        Assert.assertEquals("You should be authorised", orderApi.getMessage());
    }

}

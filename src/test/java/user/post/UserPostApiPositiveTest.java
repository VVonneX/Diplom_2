package user.post;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import steps.UserSteps;
import user.User;

public class UserPostApiPositiveTest {

    private UserSteps steps = new UserSteps();

    @Test
    @DisplayName("Creating a user is a positive test")
    @Description("Checking the status code and response of the POST request when creating a user")
    public void createUserPositiveTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        ValidatableResponse response = steps.postCreateUser(user);
        response.assertThat().statusCode(200);
        String createUserFromApi = response.extract().body().asString();
        Assert.assertTrue(createUserFromApi.contains("true"));
    }

    @Test
    @DisplayName("Authorization a user is a positive test")
    @Description("Checking the status code and response of the POST request when authorization a user")
    public void authUserTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postCreateUser(user);
        ValidatableResponse response = steps.postAuthUser(user);
        response.assertThat().statusCode(200);
        String authUserFromApi = response.extract().body().asString();
        Assert.assertTrue(authUserFromApi.contains("true"));
    }

    @After
    public void deleteUser() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        ValidatableResponse response = steps.deleteAuthUser(user);
        response.assertThat().statusCode(202);
    }
}

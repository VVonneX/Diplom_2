package user.post;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import steps.UserSteps;
import user.User;

public class UserPostApiNegativeTest {

    private UserSteps steps = new UserSteps();

    @Test
    @DisplayName("Creating a user is a negative test")
    @Description("Checking the status code and response of the POST request when creating a user duplicate")
    public void createUserNegativeTest() {
        User user = new User("churikov888@yandex.ru", "Agooddaytotesting", "Михаил");
        ValidatableResponse response = steps.postCreateUser(user);
        response.assertThat().statusCode(403);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("User already exists", createUserFromApi.getMessage());
    }

    @Test
    @DisplayName("Creating a user without a login test")
    @Description("Checking the status code and the response to the POST request when creating a user without a login")
    public void createUserWithoutALoginTest() {
        User user = new User("", "Agooddaytotesting", "Misha");
        ValidatableResponse response = steps.postCreateUser(user);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("Email, password and name are required fields", createUserFromApi.getMessage());
    }

    @Test
    @DisplayName("Creating a user without a password test")
    @Description("Checking the status code and the response to the POST request when creating a user without a password")
    public void createUserWithoutAPasswordTest() {
        User user = new User("churikov2010@yandex.ru", "", "Misha");
        ValidatableResponse response = steps.postCreateUser(user);
        response.assertThat().statusCode(403);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("Email, password and name are required fields", createUserFromApi.getMessage());
    }

    @Test
    @DisplayName("Creating a user without a name test")
    @Description("Checking the status code and the response to the POST request when creating a user without a name")
    public void createUserWithoutANameTest() {
        User user = new User("churikov2010@yandex.ru", "Agooddaytotesting", "");
        ValidatableResponse response = steps.postCreateUser(user);
        response.assertThat().statusCode(403);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("Email, password and name are required fields", createUserFromApi.getMessage());
    }

    @Test
    @DisplayName("Authorization a user without a login test")
    @Description("Checking the status code and the response to the POST request when authorization a user without a login")
    public void authUserWithoutALoginTest() {
        User user = new User("неправильный_логин", "Agooddaytotesting", "Misha");
        ValidatableResponse response = steps.postAuthUser(user);
        response.assertThat().statusCode(401);
        User authUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("email or password are incorrect", authUserFromApi.getMessage());
    }

    @Test
    @DisplayName("Authorization a user without a password test")
    @Description("Checking the status code and the response to the POST request when authorization a user without a password")
    public void authUserWithoutAPasswordTest() {
        User user = new User("churikov2000@yandex.ru", "неправильный_пароль", "Misha");
        ValidatableResponse response = steps.postAuthUser(user);
        response.assertThat().statusCode(401);
        User authUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("email or password are incorrect", authUserFromApi.getMessage());
    }

}

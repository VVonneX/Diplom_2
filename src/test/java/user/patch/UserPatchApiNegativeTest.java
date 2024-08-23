package user.patch;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import steps.UserSteps;
import user.User;

public class UserPatchApiNegativeTest {

    private UserSteps steps = new UserSteps();

    @Test
    @DisplayName("Refactor a user with login")
    @Description("Checking the status code and response of the PATCH request when refactor a duplicate email in user")
    public void patchAuthUserWithoutLoginTest() {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Михаил");
        ValidatableResponse response = steps.patchAuthUserWithoutLogin(user);
        response.assertThat().statusCode(403);
        User patchAuthApi = response.extract().body().as(User.class);
        Assert.assertEquals("User with such email already exists", patchAuthApi.getMessage());
    }

    @Test
    @DisplayName("Refactor a not authorization user is test")
    @Description("Checking the status code and response of the PATCH request when refactor a not authorization user")
    public void patchNotAuthUserTest() {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Михаил");
        ValidatableResponse response = steps.patchNotAuthUser(user);
        response.assertThat().statusCode(401);
        User patchAuthApi = response.extract().body().as(User.class);
        Assert.assertEquals("You should be authorised", patchAuthApi.getMessage());
    }
}

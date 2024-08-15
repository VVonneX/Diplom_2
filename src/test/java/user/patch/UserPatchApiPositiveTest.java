package user.patch;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import steps.UserSteps;
import user.User;

public class UserPatchApiPositiveTest {

    private UserSteps steps = new UserSteps();

    @Test
    @DisplayName("Refactor a user is a positive test")
    @Description("Checking the status code and response of the PATCH request when refactor a user")
    public void patchAuthUserTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postCreateUser(user);
        ValidatableResponse response = steps.patchAuthUser(user);
        response.assertThat().statusCode(200);
        String authPatch = response.extract().body().asString();
        Assert.assertTrue(authPatch.contains("churikov12345678@yandex.ru") && authPatch.contains("Михаил"));
    }

    @After
    public void deleteUser() {
        User user = new User("churikov12345678@yandex.ru", "Agooddaytotest", "Михаил");
        ValidatableResponse response = steps.deleteAuthUser(user);
        response.assertThat().statusCode(202);
    }
}

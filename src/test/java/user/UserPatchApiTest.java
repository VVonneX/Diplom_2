package user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class UserPatchApiTest {

    private UserSteps steps = new UserSteps();

    @Test
    @DisplayName("Refactor a user is a positive test")
    @Description("Checking the status code and response of the PATCH request when refactor a user")
    public void patchAuthUserTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postCreatePositiveUser(user);
        steps.patchAuthUser(user);
        User refactorUser = new User("churikov12345678@yandex.ru", "Agooddaytotest", "Михаил");
        steps.deleteAuthUser(refactorUser);
    }

    @Test
    @DisplayName("Refactor a user with login")
    @Description("Checking the status code and response of the PATCH request when refactor a duplicate email in user")
    public void patchAuthUserWithoutLoginTest() {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Михаил");
        steps.patchAuthUserWithoutLogin(user);
    }

    @Test
    @DisplayName("Refactor a not authorization user is test")
    @Description("Checking the status code and response of the PATCH request when refactor a not authorization user")
    public void patchNotAuthUserTest() {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Михаил");
        steps.patchNotAuthUser(user);
    }
}

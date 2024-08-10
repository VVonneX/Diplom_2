package user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class UserTest {

    private UserSteps steps = new UserSteps();

    @Test
    @DisplayName("Creating a user is a positive test")
    @Description("Checking the status code and response of the POST request when creating a user")
    public void createUserPositiveTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postCreatePositiveUser(user);
    }

    @Test
    @DisplayName("Creating a user is a negative test")
    @Description("Checking the status code and response of the POST request when creating a user duplicate")
    public void createUserNegativeTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postCreateNegativeUser(user);
    }

    @Test
    @DisplayName("Creating a user without a login test")
    @Description("Checking the status code and the response to the POST request when creating a user without a login")
    public void createUserWithoutALoginTest() {
        User user = new User("", "Agooddaytotesting", "Misha");
        steps.postCreateWithoutLoginUser(user);
    }

    @Test
    @DisplayName("Creating a user without a password test")
    @Description("Checking the status code and the response to the POST request when creating a user without a password")
    public void createUserWithoutAPasswordTest() {
        User user = new User("churikov2010@yandex.ru", "", "Misha");
        steps.postCreateWithoutPasswordUser(user);
    }

    @Test
    @DisplayName("Creating a user without a name test")
    @Description("Checking the status code and the response to the POST request when creating a user without a name")
    public void createUserWithoutANameTest() {
        User user = new User("churikov2010@yandex.ru", "Agooddaytotesting", "");
        steps.postCreateWithoutNamedUser(user);
    }

    @Test
    @DisplayName("Authorization a user is a positive test")
    @Description("Checking the status code and response of the POST request when authorization a user")
    public void authUserTest() {
        User user = new User("churikov999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postAuthUser(user);
    }

    @Test
    @DisplayName("Authorization a user without a login test")
    @Description("Checking the status code and the response to the POST request when authorization a user without a login")
    public void authUserWithoutALoginTest() {
        User user = new User("неправильный_логин", "Agooddaytotesting", "Misha");
        steps.postAuthWithoutLoginUser(user);
    }

    @Test
    @DisplayName("Authorization a user without a password test")
    @Description("Checking the status code and the response to the POST request when authorization a user without a password")
    public void authUserWithoutAPasswordTest() {
        User user = new User("churikov2000@yandex.ru", "неправильный_пароль", "Misha");
        steps.postAuthWithoutPasswordUser(user);
    }


    @Test
    @DisplayName("Refactor a user is a positive test")
    @Description("Checking the status code and response of the PATCH request when refactor a user")
    public void patchAuthUserTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.patchAuthUser(user);
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

    @Test
    @DisplayName("Courier removal is a positive test")
    @Description("Checking the status code and the response to the DELETE request")
    public void ultimateDeleteAuthUserTest() {
        User user = new User("churikov12345678@yandex.ru", "Agooddaytotest", "Михаил");
        steps.deleteAuthUser(user);
    }
}

package user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class UserPostApiTest {

    private UserSteps steps = new UserSteps();

    @Test
    @DisplayName("Creating a user is a positive test")
    @Description("Checking the status code and response of the POST request when creating a user")
    public void createUserPositiveTest() {
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postCreatePositiveUser(user);
        steps.deleteAuthUser(user);
    }

    @Test
    @DisplayName("Creating a user is a negative test")
    @Description("Checking the status code and response of the POST request when creating a user duplicate")
    public void createUserNegativeTest() {
        User user = new User("churikov888@yandex.ru", "Agooddaytotesting", "Михаил");
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
        User user = new User("churikov1999@yandex.ru", "Agooddaytotesting", "Misha");
        steps.postCreatePositiveUser(user);
        steps.postAuthUser(user);
        steps.deleteAuthUser(user);
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
}

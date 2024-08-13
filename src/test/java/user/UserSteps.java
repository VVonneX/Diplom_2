package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

public class UserSteps {

    private CreateUserApi createUserApi = new CreateUserApi();
    private DeleteUserApi deleteUserApi = new DeleteUserApi();
    private AuthorizationUserApi authorizationUserApi = new AuthorizationUserApi();
    private PatchUserApi patchUserApi = new PatchUserApi();
    private LogoutUserApi logoutUserApi = new LogoutUserApi();

    @Step("Checking the status code and response of the POST request when creating a user")
    public void postCreatePositiveUser(User exUser) {
        ValidatableResponse response = createUserApi.postCreateUser(exUser).then();
        response.assertThat().statusCode(200);
        String createUserFromApi = response.extract().body().asString();
        Assert.assertTrue(createUserFromApi.contains("true"));
    }

    @Step("Checking the status code and response of the POST request when creating a user duplicate")
    public void postCreateNegativeUser(User exUser) {
        ValidatableResponse response = createUserApi.postCreateUser(exUser).then();
        response.assertThat().statusCode(403);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("User already exists", createUserFromApi.getMessage());
    }

    @Step("Checking the status code and the response to the POST request when creating a user without a login")
    public void postCreateWithoutLoginUser(User exUser) {
        ValidatableResponse response = createUserApi.postCreateUser(exUser).then();
        response.assertThat().statusCode(403);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("Email, password and name are required fields", createUserFromApi.getMessage());
    }

    @Step("Checking the status code and the response to the POST request when creating a user without a password")
    public void postCreateWithoutPasswordUser(User exUser) {
        ValidatableResponse response = createUserApi.postCreateUser(exUser).then();
        response.assertThat().statusCode(403);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("Email, password and name are required fields", createUserFromApi.getMessage());
    }

    @Step("Checking the status code and the response to the POST request when creating a user without a name")
    public void postCreateWithoutNamedUser(User exUser) {
        ValidatableResponse response = createUserApi.postCreateUser(exUser).then();
        response.assertThat().statusCode(403);
        User createUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("Email, password and name are required fields", createUserFromApi.getMessage());
    }

    @Step("Checking the status code and the response to the DELETE request")
    public void deleteAuthUser(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        exUser = response.extract().body().as(User.class);
        String token = exUser.getAccessToken();

        ValidatableResponse responseDelete = deleteUserApi.deleteUser(token).then();
        responseDelete.assertThat().statusCode(202);
        String deleteUserFromApi = responseDelete.extract().body().asString();
        Assert.assertTrue(deleteUserFromApi.contains("true"));
    }

    @Step("Checking the status code and response of the POST request when authorization a user")
    public void postAuthUser(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        response.assertThat().statusCode(200);
        String authUserFromApi = response.extract().body().asString();
        Assert.assertTrue(authUserFromApi.contains("true"));
    }

    @Step("Checking the status code and the response to the POST request when authorization a user without a login")
    public void postAuthWithoutLoginUser(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        response.assertThat().statusCode(401);
        User authUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("email or password are incorrect", authUserFromApi.getMessage());
    }

    @Step("Checking the status code and the response to the POST request when authorization a user without a password")
    public void postAuthWithoutPasswordUser(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        response.assertThat().statusCode(401);
        User authUserFromApi = response.extract().body().as(User.class);
        Assert.assertEquals("email or password are incorrect", authUserFromApi.getMessage());
    }

    @Step("Checking the status code and the response to the PATCH request when authorization a user")
    public void patchAuthUser(User exUser) {
        //получаем токен
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        response.assertThat().statusCode(200);
        exUser = response.extract().body().as(User.class);
        String accessToken = exUser.getAccessToken();
        String refreshToken = exUser.getRefreshToken();
        //изменяем данные
        User newUserFromPath = new User("churikov12345678@yandex.ru", "Agooddaytotest", "Михаил");
        ValidatableResponse responsePatch = patchUserApi.patchAuthUser(newUserFromPath, accessToken).then();
        responsePatch.assertThat().statusCode(200);
        String patchAuthApi = responsePatch.extract().body().asString();
        Assert.assertTrue(patchAuthApi.contains("churikov12345678@yandex.ru") && patchAuthApi.contains("Михаил"));
        //разлогиниваемся из системы
        ValidatableResponse logout = logoutUserApi.postUser(accessToken, refreshToken).then();
        logout.assertThat().statusCode(200);
        //входим в систему и проверяем, что изменились все данные, включая пароль
        ValidatableResponse responseLastAuth = authorizationUserApi.postAuthUser(newUserFromPath).then();
        responseLastAuth.assertThat().statusCode(200);
        String authLast = responseLastAuth.extract().body().asString();
        Assert.assertTrue(authLast.contains("churikov12345678@yandex.ru") && authLast.contains("Михаил"));
    }

    @Step("Checking the status code and response of the PATCH request when refactor a duplicate email in user")
    public void patchAuthUserWithoutLogin(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        response.assertThat().statusCode(200);
        exUser = response.extract().body().as(User.class);
        String accessToken = exUser.getAccessToken();

        User newUserFromPath = new User("churikov2020@yandex.ru", "Agooddaytotest", "Чуриков");
        ValidatableResponse responsePatch = patchUserApi.patchAuthUser(newUserFromPath, accessToken).then();
        responsePatch.assertThat().statusCode(403);
        User patchAuthApi = responsePatch.extract().body().as(User.class);
        Assert.assertEquals("User with such email already exists", patchAuthApi.getMessage());
    }

    @Step("Checking the status code and response of the PATCH request when refactor a not authorization user")
    public void patchNotAuthUser(User exUser) {
        ValidatableResponse responsePatch = patchUserApi.patchNotAuthUser(exUser).then();
        responsePatch.assertThat().statusCode(401);
        User patchAuthApi = responsePatch.extract().body().as(User.class);
        Assert.assertEquals("You should be authorised", patchAuthApi.getMessage());
    }

}
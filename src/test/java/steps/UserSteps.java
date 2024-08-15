package steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import user.*;

public class UserSteps {

    private CreateUserApi createUserApi = new CreateUserApi();
    private DeleteUserApi deleteUserApi = new DeleteUserApi();
    private AuthorizationUserApi authorizationUserApi = new AuthorizationUserApi();
    private PatchUserApi patchUserApi = new PatchUserApi();
    private LogoutUserApi logoutUserApi = new LogoutUserApi();

    @Step("Creating a user")
    public ValidatableResponse postCreateUser(User exUser) {
        return createUserApi.postCreateUser(exUser).then();
    }

    @Step("Checking the status code and the response to the DELETE request")
    public ValidatableResponse deleteAuthUser(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        exUser = response.extract().body().as(User.class);
        String token = exUser.getAccessToken();
        return deleteUserApi.deleteUser(token).then();
    }

    @Step("Authorization a user")
    public ValidatableResponse postAuthUser(User exUser) {
        return authorizationUserApi.postAuthUser(exUser).then();
    }

    @Step("Checking the status code and the response to the PATCH request when authorization a user")
    public ValidatableResponse patchAuthUser(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        exUser = response.extract().body().as(User.class);
        String accessToken = exUser.getAccessToken();
        String refreshToken = exUser.getRefreshToken();
        User newUserFromPath = new User("churikov12345678@yandex.ru", "Agooddaytotest", "Михаил");
        patchUserApi.patchAuthUser(newUserFromPath, accessToken).then();
        logoutUserApi.postUser(accessToken, refreshToken).then();
        return authorizationUserApi.postAuthUser(newUserFromPath).then();
    }

    @Step("Checking the status code and response of the PATCH request when refactor a duplicate email in user")
    public ValidatableResponse patchAuthUserWithoutLogin(User exUser) {
        ValidatableResponse response = authorizationUserApi.postAuthUser(exUser).then();
        exUser = response.extract().body().as(User.class);
        String accessToken = exUser.getAccessToken();
        User newUserFromPatch = new User("churikov2020@yandex.ru", "Agooddaytotest", "Чуриков");
        return patchUserApi.patchAuthUser(newUserFromPatch, accessToken).then();
    }

    @Step("Checking the status code and response of the PATCH request when refactor a not authorization user")
    public ValidatableResponse patchNotAuthUser(User exUser) {
        return patchUserApi.patchNotAuthUser(exUser).then();
    }

}
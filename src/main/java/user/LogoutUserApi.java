package user;

import baseurl.BaseHttpClient;
import io.restassured.response.Response;

public class LogoutUserApi extends BaseHttpClient {
    private final String apiPathLogout = "/api/auth/logout";

    public Response postUser(String accessToken, String refreshToken) {
        return doAuthPostRequest(apiPathLogout, accessToken, refreshToken);
    }
}

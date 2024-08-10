package user;

import baseurl.BaseHttpClient;
import io.restassured.response.Response;

public class AuthorizationUserApi extends BaseHttpClient {
    private final String apiPathCreate = "/api/auth/login";

    public Response postAuthUser(User user) {
        return doNotAuthPostRequest(apiPathCreate, user);
    }
}

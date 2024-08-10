package user;

import baseurl.BaseHttpClient;
import io.restassured.response.Response;

public class DeleteUserApi extends BaseHttpClient {
    private final String apiPathDelete = "/api/auth/user";

    public Response deleteUser(String token) {
        return doDeleteRequest(apiPathDelete, token);
    }
}

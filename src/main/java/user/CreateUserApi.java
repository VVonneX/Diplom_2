package user;

import baseurl.BaseHttpClient;
import io.restassured.response.Response;

public class CreateUserApi extends BaseHttpClient {
    private final String apiPathCreate = "/api/auth/register";

    public Response postCreateUser(User user) {
        return doPostRequest(apiPathCreate, user);
    }

}

package user;

import baseurl.BaseHttpClient;
import io.restassured.response.Response;

public class PatchUserApi extends BaseHttpClient {
    private final String apiPathPatch = "/api/auth/user";

    public Response patchUser(User user, String token) {
        return doAuthPatchRequest(apiPathPatch, user, token);
    }

    public Response patchNotAuthUser(User user) {
        return doNotAuthPatchRequest(apiPathPatch, user);
    }


}

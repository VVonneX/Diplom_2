package order;

import baseurl.BaseHttpClient;
import io.restassured.response.Response;

public class OrderApi extends BaseHttpClient {
    private final String apiPath = "/api/orders";

    public Response createNotAuthOrder(Order order) {
        return doNotAuthPostRequest(apiPath, order);
    }

    public Response createAuthOrder(Order order, String token) {
        return doAuthPostRequest(apiPath, order, token);
    }

    public Response getAuthOrders(String token) {
        return doGetAuthRequest(apiPath, token);
    }

    public Response getNotAuthOrders() {
        return doGetNotAuthRequest(apiPath);
    }
}

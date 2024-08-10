package baseurl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import user.User;

import static io.restassured.RestAssured.given;

public class BaseHttpClient {
    private RequestSpecification baseRequestSpecNoAuth() {
        return new RequestSpecBuilder()
                .setBaseUri(URL.HOST)
                .addHeader("Content-type", "application/json")
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    private RequestSpecification baseRequestSpecAuth(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(URL.HOST)
                .addHeader("Authorization",  token)
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    protected Response doPostRequest(String path, Object body) {
        return given()
                .spec(baseRequestSpecNoAuth())
                .body(body)
                .post(path)
                .thenReturn();
    }

    protected Response doAuthPatchRequest(String path, Object body, String token) {
        return given()
                .spec(baseRequestSpecAuth(token))
                .header("Content-type", "application/json")
                .body(body)
                .patch(path)
                .thenReturn();
    }

    protected Response doNotAuthPatchRequest(String path, Object body) {
        return given()
                .spec(baseRequestSpecNoAuth())
                .header("Content-type", "application/json")
                .body(body)
                .patch(path)
                .thenReturn();
    }

    protected Response doAuthPostRequest(String path, String token1, String token2) {
        return given()
                .spec(baseRequestSpecAuth(token1))
                .header("Content-type", "application/json")
                .body("{\"token\" : " + "\"" + token2 + "\"}")
                .post(path)
                .thenReturn();
    }

    protected Response doDeleteRequest(String path, String token) {
        return given()
                .spec(baseRequestSpecAuth(token))
                .delete(path)
                .thenReturn();
    }


}

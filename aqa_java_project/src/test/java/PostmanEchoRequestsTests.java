import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.*;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

public class PostmanEchoRequestsTests {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void getRequest_RestAssured() {
        given().queryParam("foo1", "bar1")
            .queryParam("foo2", "bar2")
            .when()
            .get("/get")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("args.foo1", equalTo("bar1"))
            .body("args.foo2", equalTo("bar2"));
    }

    @Test
    public void getRequest_JUnit() {
        Response response = given()
            .queryParam("foo1", "bar1")
            .queryParam("foo2", "bar2")
            .get("/get");

        assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        assertEquals("bar1", json.getString("args.foo1"));
        assertEquals("bar2", json.getString("args.foo2"));
    }

    @Test
    public void postRawTextRequest_RestAssured() {
        String requestBody = "{ \"test\": \"value\" }";

        given().header("Content-Type", "text/plain")
            .body(requestBody)
            .when()
            .post("/post")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("data", equalTo(requestBody));
    }

    @Test
    public void postRawTextRequest_JUnit() {
        String requestBody = "{ \"test\": \"value\" }";

        Response response = given()
            .header("Content-Type", "text/plain")
            .body(requestBody)
            .post("/post");

        assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        assertEquals(requestBody, json.getString("data"));
    }

    @Test
    public void postFormDataRequest_RestAssured() {
        given().multiPart("foo1", "bar1")
            .multiPart("foo2", "bar2")
            .when()
            .post("/post")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("form.foo1", equalTo("bar1"))
            .body("form.foo2", equalTo("bar2"));
    }

    @Test
    public void postFormDataRequest_JUnit() {
        Response response = given()
            .multiPart("foo1", "bar1")
            .multiPart("foo2", "bar2")
            .post("/post");

        assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        assertEquals("bar1", json.getString("form.foo1"));
        assertEquals("bar2", json.getString("form.foo2"));
    }

    @Test
    public void putRequest_RestAssured() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given().header("Content-Type", "text/plain")
            .body(requestBody)
            .when()
            .put("/put")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("data", equalTo(requestBody));
    }

    @Test
    public void putRequest_JUnit() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
            .header("Content-Type", "text/plain")
            .body(requestBody)
            .put("/put");

        assertEquals(200, response.statusCode());
        assertTrue(response.contentType().contains("application/json"));

        JsonPath json = response.jsonPath();
        assertEquals(requestBody, json.getString("data"));
    }

    @Test
    public void patchRequest_RestAssured() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given().header("Content-Type", "text/plain")
            .body(requestBody)
            .when()
            .patch("/patch")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("data", equalTo(requestBody));
    }

    @Test
    public void patchRequest_JUnit() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
            .header("Content-Type", "text/plain")
            .body(requestBody)
            .patch("/patch");

        assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        assertEquals(requestBody, json.getString("data"));
    }

    @Test
    public void deleteRequest_RestAssured() {
        given().when()
            .delete("/delete")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("url", containsString("/delete"))
            .body("data", equalTo(emptyMap()));
    }

    @Test
    public void deleteRequest_JUnit() {
        Response response = given().delete("/delete");

        assertEquals(200, response.statusCode());

        JsonPath json = response.jsonPath();
        assertTrue(json.getString("url").contains("/delete"));
        assertEquals(emptyMap(), json.getMap("data"));
    }
}
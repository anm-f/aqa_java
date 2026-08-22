import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.*;
import static java.util.Collections.emptyMap;
import static org.hamcrest.Matchers.*;

public class EchoApiTests {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequest() {
        given()
                .queryParam("foo1", "bar1")
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
    public void testPostRawText() {
        String requestBody = "{ \"test\": \"value\" }";

        given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testPostFormData() {
        given()
                .multiPart("foo1", "bar1")
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
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testDeleteRequest() {
        given()
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("url", containsString("/delete"))
                .body("data", equalTo(emptyMap()));
    }
}
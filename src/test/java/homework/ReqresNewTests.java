package homework;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class ReqresNewTests {

    @Test
    void sizeListOfUsers() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void checkSingleUserLastName() {
        given()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void checkCreateUser() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"morpheus\", \"job\": \"leader\" }")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)//проверили статус
                .body("name", is("morpheus"))
                .and()
                .body("job", is("leader"));
    }

    @Test
    void unsuccessfulRegistration() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"sydney@fife\" }")
                .when()
                .post(" https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void successfulRegistration() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }")
                .when()
                .post(" https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }




}








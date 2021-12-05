package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class ReqresNewTests {

    @Test
    @Tag("allure_log")
    void sizeListOfUsers() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    @Tag("allure_log")
    void checkSingleUserLastName() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.last_name", is("Weaver"));
    }

    @Test
    @Tag("allure_log")
    void checkCreateUser() {
        given()
                .filter(customLogFilter().withCustomTemplates())
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
    @Tag("allure_log")
    void unsuccessfulRegistration() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"sydney@fife\" }")
                .when()
                .post(" https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @Tag("allure_log")
    void successfulRegistration() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }")
                .when()
                .post(" https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}








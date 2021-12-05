package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class BookStoreTest {

    @Test
    void noLogsTest() {
        given()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .body("books", hasSize(greaterThan(0)));

    }

    @Test
    void withAlllogsTest() {
        given()
                .log().all()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().all()
                .body("books", hasSize(greaterThan(0)));

    }

    @Test
    void withSomeLogsTest() {
        given()
                .log().uri()
                .log().body()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().body()
                .body("books", hasSize(greaterThan(0)));

    }

    @Test
    @Tag("allure_log")
    void autorizeTest() {

        String data = "{" +
                "  \"userName\": \"alex\"," +
                "  \"password\": \"asdsad#frew_DFS2\"" +
                "}";

//        Map<String, String> data = new HashMap<>();
//        data.put("userName", "alex");
//        data.put("password", "asdsad#frew_DFS2");

        given()
                .contentType("application/json")
                .accept("application/json")
                .body(data.toString())
                .when()
                .log().uri()
                .log().body()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }


    @Test
    void allureWithListenerTest() {

//        String data = "{" +
//                "  \"userName\": \"valentina\"," +
//                "  \"password\": \"Q_we#123456\"" +
//                "}";

        String data = "{" +
                "  \"userName\": \"alex\"," +
                "  \"password\": \"asdsad#frew_DFS2\"" +
                "}";

        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .log().uri()
                .log().body()
                .body(data.toString())

                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }

    @Test
        // @Tag("allure_log")
    void allureWithTemplatesTest() {

        String data = "{" +
                "  \"userName\": \"alex\"," +
                "  \"password\": \"asdsad#frew_DFS2\"" +
                "}";

        step("Generate token", () ->
                given()
                        //  .filter(customLogFilter().withCustomTemplates())
                        .filter(new AllureRestAssured())
                        .contentType(ContentType.JSON)
                        .log().uri()
                        .log().body()
                        .body(data.toString())

                        .post("https://demoqa.com/Account/v1/GenerateToken")
                        .then()
                        .log().body()
                        .body("status", is("Success"))
                        .body("result", is("User authorized successfully."))
        );

        step("Any UI action");
    }


    @Test
    void allureWithSchemeTest() {

        String data = "{" +
                "  \"userName\": \"alex\"," +
                "  \"password\": \"asdsad#frew_DFS2\"" +
                "}";

        given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .accept("application/json")
                .body(data.toString())
                .when()
                .log().uri()
                .log().body()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath("schemas/GenerateTokenSheme.json"))
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));
    }

}

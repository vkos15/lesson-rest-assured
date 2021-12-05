package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class ReqresTest {

    @Test
    void  successfulLogin(){
       // https://reqres.in/api/login
//то что хотим отправить
       /* {
            "email": "eve.holt@reqres.in",
                "password": "cityslicka"
        }*/
        /*
        //ответ
        {
            "token": "QpwL5tke4Pnpja7X4"
        }
*/
        //status 200

        given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)//проверили статус
                .body("token", is("QpwL5tke4Pnpja7X4")); //проверили ответ
    }

    @Test
    void  negativeLogin(){
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\" }")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)//проверили статус
                .body("error", is("Missing password")); //проверили ответ

    }
}

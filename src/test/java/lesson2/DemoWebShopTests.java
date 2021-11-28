package lesson2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class DemoWebShopTests {

//    curl 'http://demowebshop.tricentis.com/addproducttocart/details/72/1' \
//            -H 'Connection: keep-alive' \
//            -H 'Accept: */*' \
//            -H 'X-Requested-With: XMLHttpRequest' \
//            -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36' \
//            -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8' \
//            -H 'Origin: http://demowebshop.tricentis.com' \
//            -H 'Referer: http://demowebshop.tricentis.com/build-your-cheap-own-computer' \
//            -H 'Accept-Language: ru,uk;q=0.9,en-US;q=0.8,en;q=0.7,ru-RU;q=0.6' \
//            -H 'Cookie: Nop.customer=0bc96a2e-9daa-4b1a-ba12-02fbc4b46602; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utma=78382081.2023250207.1638001471.1638001471.1638001471.1; __utmc=78382081; __utmz=78382081.1638001471.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72; __atuvc=6%7C47; __atuvs=61a1eb47026e5758005; __utmb=78382081.12.10.1638001471' \
//            --data-raw 'product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1' \
//            --compressed \
//            --insecure

    @Test
    void addToCartTest() {

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"))
                .extract().response();

        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());

//        "success": true,
//                "message": "The product has been added to your <a href=\"/cart\">shopping cart</a>

    }

    @Test
    void addToCartWithCookieTest() {

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .cookie("Nop.customer=0bc96a2e-9daa-4b1a-ba12-02fbc4b46602;")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)")) // todo проверить увеличение на 1
                .extract().response();

        System.out.println(response.asString());
        System.out.println(response.path("updatetopcartsectionhtml").toString());

//        "success": true,
//                "message": "The product has been added to your <a href=\"/cart\">shopping cart</a>

    }
}

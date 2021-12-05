package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DemoWebShopNewTests {

    //добавляем товар в wishlist через апи, очищаем через UI
    @Test
    void wishListTest() {

//        given()
//                .filter(customLogFilter().withCustomTemplates())
//                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                .cookie("Nop.customer=34bfae66-f0f6-4f92-b943-0438cb7489f4;")
//                .body("addtocart_78.EnteredQuantity=1")
//                .when()
//                .post("http://demowebshop.tricentis.com/addproducttocart/details/78/2")
//                .then()
//                .statusCode(200)
//                .body("success", is(true))
//                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
//                .body("updatetopwishlistsectionhtml", is("(1)"));

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        //добавили куки, перед этим открыли "легкую" страничку сайта, чтобы можно было их добавить
        getWebDriver().manage().addCookie(
                new Cookie("Nop.customer", "34bfae66-f0f6-4f92-b943-0438cb7489f4"));

        // открыли виш-лист в котором уже есть 1 добавленный товар
        open("http://demowebshop.tricentis.com/wishlist");
        //удалили товар через UI и проверили, что список стал пуст
        $(".remove-from-cart input[name='removefromcart']").click();
        $(byValue("Update wishlist")).click();
        $(".wishlist-content").shouldHave(text("The wishlist is empty!"));

    }
}

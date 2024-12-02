package tests;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;


public class SelenideSoftAssertions {

    @BeforeAll
    static void beforeAllSetup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";

    }

    @Test
    void TestShouldFindSelenideSoftAssertions() {
        // открыть страницу репозитория селенида
        open("/selenide/selenide");

        //переход, поиск и проверка в wiki
        $("[id=wiki-tab]").click();
        $("[id=wiki-pages-filter]").setValue("SoftAssertions");
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        //переход и проверка кода Junit5
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text(
                "@ExtendWith({SoftAssertsExtension.class}) \n" +
                        "class Tests { \n" +
                            "@Test \n" +
                            "void test() { \n" +
                                "Configuration.assertionMode = SOFT; \n" +
                                "open(\"page.html\"); \n" +
                                " \n" +
                                "$(\"#first\").should(visible).click(); \n" +
                                "$(\"#second\").should(visible).click(); \n" +
                            "} \n" +
                        "} \n"));

    }
}

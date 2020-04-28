import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class TestFaces {
    @BeforeClass
    public static void before() {
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void EnterLongPosName() {
        open("http://localhost:7000/");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $("body > div > div > div > ul > li:nth-child(2) > div > a > h4").click();
        $(By.id("name")).click();
        $(By.id("name")).sendKeys("TestDataTestDataTestDataTestDataTestDataTestDataTestDataTestData" +
                "TestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestD" +
                "ataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTestDataTe" +
                "stDataTestData");//264 символа.
        $(By.cssSelector("#new-title-form > div > div > main > div > div > button:nth-child(1)")).click();
        $("body > h1").shouldHave(exactText("We are working on your problem!"));
        closeWindow();
    }

    @Test
    public void CheckSaveButton() {
        open("http://localhost:7000/");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $("body > div > div > div > ul > li:nth-child(2) > div > a > h4").click();
        $(By.id("name")).click();
        $(By.id("name")).sendKeys("TestData");//Валидные данные.
        $(By.cssSelector("#new-title-form > div > div > main > div > div > button:nth-child(1)")).click();
        $("#new-title-form > div > div > main > table").shouldHave((exactText("TestData")));
        closeWindow();
    }

    @Test
    public void ChangeSignActivitySeveralPos () {
        open("http://localhost:7000/");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $("body > div > div > div > ul > li:nth-child(2) > div > a > h4").click();
        $(By.id("blankCheckbox")).click();
        $("tr:nth-child(2) #blankCheckbox").click();
        $(By.cssSelector("#new-title-form > div > div > main > div > div > button:nth-child(4)")).click();
        $("#new-title-form > div > div > main > table > tbody > tr:nth-child(1) > td:nth-child(3) > button.btn.btn.btn.btn-danger").shouldHave(exactText("Inactive"));
        $("tr:nth-child(2) .btn-danger").shouldHave(exactText("Inactive"));
        $(By.id("blankCheckbox")).click();
        $("tr:nth-child(2) #blankCheckbox").click();
        $(By.cssSelector("#new-title-form > div > div > main > div > div > button:nth-child(4)")).click();
        $("#new-title-form > div > div > main > table > tbody > tr:nth-child(1) > td:nth-child(3) > button.btn.btn.btn.btn-success").shouldHave(exactText("Active"));
        $("tr:nth-child(2) .btn-success").shouldHave(exactText("Active"));
        //closeWindow();
    }

    @Test
    public void ChangeSignActivityOnePos () {
        open("http://localhost:7000/");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $("body > div > div > div > ul > li:nth-child(2) > div > a > h4").click();
        $(By.name("switchById")).click();
        $(By.name("switchById")).shouldHave(exactText("Inactive"));
        $(By.name("switchById")).click();
        $(By.name("switchById")).shouldHave(exactText("Active"));
        closeWindow();
    }

    @Test
    public void SortPositionByName () {
        open("http://localhost:7000/");
        $(By.id("login")).sendKeys("user");
        $(By.id("pwd")).sendKeys("user");
        $(By.id("signIn")).click();
        $("body > div > div > div > ul > li:nth-child(2) > div > a > h4").click();
        $(By.id("name")).click();
        $(By.id("name")).sendKeys("AutoTest");//Валидные данные.
        $(By.cssSelector("#new-title-form > div > div > main > div > div > button:nth-child(1)")).click();
        $("#new-title-form > div > div > main > div > div > button:nth-child(3)").click();
        $("#new-title-form > div > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2) > label").shouldHave(exactText("AutoTest"));
        closeWindow();
    }
}
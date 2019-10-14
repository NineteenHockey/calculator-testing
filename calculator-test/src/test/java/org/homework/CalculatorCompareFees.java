package org.homework;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CalculatorCompareFees {
    private static SelenideElement monthlyFee = $(By.id("homeInsuranceStartPrice"));
    private static SelenideElement cookiesConfirmation = $(By.className("buttons"));

    @BeforeClass
    public static void setup() {
        //Configuration.baseUrl = "https://swedbank.ee/private/insurance/home/ihome?language=ENG";
        Configuration.startMaximized=true;
    }

    @Test
    public void compareAppartmentAndResidentualBuilding() {
        //apartment should be more expensive than residential?
        float apartment;
        float residential;
        open("https://swedbank.ee/private/insurance/home/ihome?language=ENG");
        if (cookiesConfirmation.exists())
        {
            cookiesConfirmation.click();
        }
        $("#radio1").selectRadio("KORTER");
        $("#radio3").selectRadio("KIVI");
        $("#calculateActive").click();
        apartment = Float.parseFloat(monthlyFee.getText());
        $("#radio2").selectRadio("HOONE");
        //$("#radio3").selectRadio("KIVI");
        $("#calculateActive").click();
        residential = Float.parseFloat(monthlyFee.getText());
        assertTrue(apartment>residential);



    }
}

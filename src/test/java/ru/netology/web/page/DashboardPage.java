package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement increaseButton = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] .button");
    private String balance = $(By.xpath("//*[@id=\"root\"]/div/ul/li[1]/div/text()[3]")).getText();
    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage increaseTransfer() {
        increaseButton.click();
        return new TransferPage();
    }

    public int getCurrentBalance() {
        return Integer.parseInt (balance);
    }

    public int getExpectedBalance(int transfer) {
       int currentBalance = getCurrentBalance();
        return currentBalance + transfer;
    }
}

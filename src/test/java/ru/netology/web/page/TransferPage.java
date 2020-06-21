package ru.netology.web.page;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement amount = $("[data-test-id = amount] input");
    private SelenideElement from = $("[data-test-id = from] input");
    private SelenideElement transferButton = $("[data-test-id = action-transfer]");

    public TransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage moneyTransfer(DataHelper.TransferInfo info) {
        amount.setValue(info.getAmount());
        from.setValue(info.getCard());
        transferButton.click();
        return new DashboardPage();
    }
}

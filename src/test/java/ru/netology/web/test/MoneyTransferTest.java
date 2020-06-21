package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        val expectedBalance = dashboardPage.getExpectedBalance(200);
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val amount = "200";
        val transferInfo = DataHelper.getTransferInfo(amount);
        transferPage.moneyTransfer(transferInfo);
        val finalBalance = dashboardPage.getCurrentBalance();
        assertEquals(expectedBalance,finalBalance);
    }
}

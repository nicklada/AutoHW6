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
    void shouldTransferMoney() {
        open("http://localhost:9999");
        val amount = 200;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        val expectedBalance = dashboardPage.getExpectedBalance(amount);
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = DataHelper.getTransferInfo(String.valueOf(amount));
        transferPage.moneyTransfer(transferInfo);
        val finalBalance = dashboardPage.getCurrentBalance();
        assertEquals(expectedBalance,finalBalance);
    }

    @Test
    void shouldBeErrorWhenAmountEmpty() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = new DataHelper.TransferInfo("","5559000000000002");
        transferPage.invalidMoneyTransfer(transferInfo);
    }

    @Test
    void shouldBeErrorWhenCardFieldEmpty() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = new DataHelper.TransferInfo("200","");
        transferPage.invalidMoneyTransfer(transferInfo);
    }

    @Test
    void shouldBeErrorWhenCardIrrelevant() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = new DataHelper.TransferInfo("200","5559000000000025");
        transferPage.invalidMoneyTransfer(transferInfo);
    }

    @Test
    void shouldTransferNothingWhenAmountZero() {
        open("http://localhost:9999");
        val amount = 0;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        val expectedBalance = dashboardPage.getExpectedBalance(amount);
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = DataHelper.getTransferInfo(String.valueOf(amount));
        transferPage.moneyTransfer(transferInfo);
        val finalBalance = dashboardPage.getCurrentBalance();
        assertEquals(expectedBalance,finalBalance);
    }

    @Test
    void shouldTransferWhenAmountIsOne() {
        open("http://localhost:9999");
        val amount = 1;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        val expectedBalance = dashboardPage.getExpectedBalance(amount);
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = DataHelper.getTransferInfo(String.valueOf(amount));
        transferPage.moneyTransfer(transferInfo);
        val finalBalance = dashboardPage.getCurrentBalance();
        assertEquals(expectedBalance,finalBalance);
    }

    @Test
    void shouldTransferAllMoney() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        val amount = dashboardPage.getCurrentBalanceBack();
        val expectedBalance = dashboardPage.getExpectedBalance(amount);
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = DataHelper.getTransferInfo(String.valueOf(amount));
        transferPage.moneyTransfer(transferInfo);
        val finalBalance = dashboardPage.getCurrentBalance();
        assertEquals(expectedBalance,finalBalance);
    }

    @Test
    void shouldBeErrorWhenNotEnoughMoneyToTransfer() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        val currentAmount = dashboardPage.getCurrentBalanceBack();
        val amount = currentAmount+1;
        dashboardPage.increaseTransfer();
        val transferPage = new TransferPage();
        val transferInfo = DataHelper.getTransferInfo(String.valueOf(amount));
        transferPage.invalidMoneyTransfer(transferInfo);
    }
}

package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.CreditPage;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DbUtils.getCreditStatus;
import static ru.netology.data.DbUtils.getLastCreditId;

public class CreditTest extends BaseTest {

    @Test
    @DisplayName("Успешная покупка в кредит APPROVED картой")
    void shouldSuccessBuyWithCreditApprovedCard() {
        var mainPage = new MainPage();
        var card = getApprovedCard();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifySuccessNotification();

        String creditId = getLastCreditId();
        assertThat(creditId).isNotNull();

        String status = getCreditStatus(creditId);
        assertThat(status).isEqualTo("APPROVED");
    }

    @Test
    @DisplayName("Кредит с пустым номером карты")
    void shouldShowErrorForEmptyCardNumber() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyNumber();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyInvalidFormatMessage();
    }

    @Test
    @DisplayName("Кредит с истекшим месяцем")
    void shouldShowErrorForExpiredMonth() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredMonth();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyInvalidDateMessage();
    }

    @Test
    @DisplayName("Кредит с истекшим годом")
    void shouldShowErrorForExpiredYear() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredYear();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyExpiredDateMessage();
    }

    @Test
    @DisplayName("Кредит с CVC из 2 цифр")
    void shouldShowErrorForShortCvc() {
        var mainPage = new MainPage();
        var card = getCardWithShortCvc();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyInvalidFormatMessage();
    }
}
package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DbUtils.getLastPaymentId;
import static ru.netology.data.DbUtils.getPaymentStatus;

public class PaymentTest extends BaseTest {

    @Test
    @DisplayName("Успешная оплата APPROVED картой")
    void shouldSuccessPayWithApprovedCard() {
        var mainPage = new MainPage();
        var card = getApprovedCard();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifySuccessNotification();

        String paymentId = getLastPaymentId();
        assertThat(paymentId).isNotNull();

        String status = getPaymentStatus(paymentId);
        assertThat(status).isEqualTo("APPROVED");
    }

    @Test
    @DisplayName("Оплата с пустым номером карты")
    void shouldShowErrorForEmptyCardNumber() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyNumber();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyInvalidFormatMessage();
    }

    @Test
    @DisplayName("Оплата с истекшим месяцем")
    void shouldShowErrorForExpiredMonth() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredMonth();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyInvalidDateMessage();
    }

    @Test
    @DisplayName("Оплата с истекшим годом")
    void shouldShowErrorForExpiredYear() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredYear();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyExpiredDateMessage();
    }

    @Test
    @DisplayName("Оплата с CVC из 2 цифр")
    void shouldShowErrorForShortCvc() {
        var mainPage = new MainPage();
        var card = getCardWithShortCvc();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyInvalidFormatMessage();
    }
}
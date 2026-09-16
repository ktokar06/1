package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DbUtils.*;

public class PaymentTest extends BaseTest {

    @Test
    @DisplayName("Успешная оплата APPROVED картой")
    void shouldSuccessPayWithApprovedCard() {
        var mainPage = new MainPage();
        var card = getApprovedCard();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyNotification("Успешно");

        String paymentId = getLastPaymentId();
        assertEquals("APPROVED", getPaymentStatus(paymentId));
        assertEquals(1, countPaymentRequests());
    }

    @Test
    @DisplayName("Оплата с пустым номером карты")
    void shouldShowErrorForEmptyCardNumber() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyNumber();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с пустым месяцем")
    void shouldShowErrorForEmptyMonth() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyMonth();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с пустым годом")
    void shouldShowErrorForEmptyYear() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyYear();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с пустым владельцем")
    void shouldShowErrorForEmptyHolder() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyHolder();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Оплата с пустым CVC")
    void shouldShowErrorForEmptyCvc() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyCvc();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Оплата с истекшим месяцем")
    void shouldShowErrorForExpiredMonth() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredMonth();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Оплата с истекшим годом")
    void shouldShowErrorForExpiredYear() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredYear();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Оплата с CVC из 2 цифр")
    void shouldShowErrorForShortCvc() {
        var mainPage = new MainPage();
        var card = getCardWithShortCvc();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с владельцем на кириллице — ошибка валидации")
    void shouldShowErrorForCyrillicHolder() {
        var mainPage = new MainPage();
        var card = getCardWithCyrillicHolder();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с владельцем, содержащим цифры — ошибка валидации")
    void shouldShowErrorForDigitsInHolder() {
        var mainPage = new MainPage();
        var card = getCardWithDigitsInHolder();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с владельцем, содержащим спецсимволы — ошибка валидации")
    void shouldShowErrorForSpecialCharsInHolder() {
        var mainPage = new MainPage();
        var card = getCardWithSpecialCharsInHolder();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с владельцем из одного слова — ошибка валидации")
    void shouldShowErrorForOneWordHolder() {
        var mainPage = new MainPage();
        var card = getCardWithOneWordHolder();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата с владельцем из одной буквы — ошибка валидации")
    void shouldShowErrorForOneCharHolder() {
        var mainPage = new MainPage();
        var card = getCardWithOneCharHolder();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Оплата DECLINED картой")
    void shouldShowErrorForDeclinedCard() {
        var mainPage = new MainPage();
        var card = getDeclinedCard();

        PaymentPage paymentPage = mainPage.choosePaymentByCard();
        paymentPage.fillForm(card);
        paymentPage.verifyNotification("Ошибка");
        paymentPage.verifyNoValidationMessage();

        String paymentId = getLastPaymentId();
        assertEquals("DECLINED", getPaymentStatus(paymentId));
        assertEquals(1, countPaymentRequests());
    }
}
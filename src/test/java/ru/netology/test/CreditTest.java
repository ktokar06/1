package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.CreditPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DbUtils.*;

public class CreditTest extends BaseTest {

    @Test
    @DisplayName("Успешная покупка в кредит APPROVED картой")
    void shouldSuccessBuyWithCreditApprovedCard() {
        var mainPage = new MainPage();
        var card = getApprovedCard();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNotification("Успешно");

        String creditId = getLastCreditId();
        assertEquals("APPROVED", getCreditStatus(creditId));
        assertEquals(1, countCreditRequests());
    }

    @Test
    @DisplayName("Кредит с пустым номером карты")
    void shouldShowErrorForEmptyCardNumber() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyNumber();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Кредит с пустым месяцем")
    void shouldShowErrorForEmptyMonth() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyMonth();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Кредит с пустым годом")
    void shouldShowErrorForEmptyYear() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyYear();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Кредит с пустым владельцем")
    void shouldShowErrorForEmptyHolder() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Кредит с пустым CVC")
    void shouldShowErrorForEmptyCvc() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyCvc();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Кредит с истекшим месяцем")
    void shouldShowErrorForExpiredMonth() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredMonth();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Кредит с истекшим годом")
    void shouldShowErrorForExpiredYear() {
        var mainPage = new MainPage();
        var card = getCardWithExpiredYear();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Кредит с CVC из 2 цифр")
    void shouldShowErrorForShortCvc() {
        var mainPage = new MainPage();
        var card = getCardWithShortCvc();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyValidationMessage("Неверный формат");
    }

    @Test
    @DisplayName("Кредит с владельцем на кириллице")
    void shouldAcceptCyrillicHolder() {
        var mainPage = new MainPage();
        var card = getCardWithCyrillicHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем, содержащим цифры")
    void shouldAcceptDigitsInHolder() {
        var mainPage = new MainPage();
        var card = getCardWithDigitsInHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем, содержащим спецсимволы")
    void shouldAcceptSpecialCharsInHolder() {
        var mainPage = new MainPage();
        var card = getCardWithSpecialCharsInHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем из одного слова")
    void shouldAcceptOneWordHolder() {
        var mainPage = new MainPage();
        var card = getCardWithOneWordHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем из одной буквы")
    void shouldAcceptOneCharHolder() {
        var mainPage = new MainPage();
        var card = getCardWithOneCharHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с DECLINED картой")
    void shouldShowErrorForDeclinedCard() {
        var mainPage = new MainPage();
        var card = getDeclinedCard();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }
}
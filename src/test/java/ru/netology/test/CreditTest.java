package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.CreditPage;

import static ru.netology.data.DataHelper.*;

public class CreditTest extends BaseTest {

    @Test
    @DisplayName("Успешная покупка в кредит APPROVED картой")
    void shouldSuccessBuyWithCreditApprovedCard() {
        var mainPage = new MainPage();
        var card = getApprovedCard();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
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
    @DisplayName("Кредит с пустым месяцем")
    void shouldShowErrorForEmptyMonth() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyMonth();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyInvalidFormatMessage();
    }

    @Test
    @DisplayName("Кредит с пустым годом")
    void shouldShowErrorForEmptyYear() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyYear();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyInvalidFormatMessage();
    }

    @Test
    @DisplayName("Кредит с пустым владельцем")
    void shouldShowErrorForEmptyHolder() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyRequiredFieldMessage();
    }

    @Test
    @DisplayName("Кредит с пустым CVC")
    void shouldShowErrorForEmptyCvc() {
        var mainPage = new MainPage();
        var card = getCardWithEmptyCvc();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyRequiredFieldMessage();
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

    @Test
    @DisplayName("Кредит с владельцем на кириллице")
    void shouldShowErrorForCyrillicHolder() {
        var mainPage = new MainPage();
        var card = getCardWithCyrillicHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем, содержащим цифры")
    void shouldShowErrorForDigitsInHolder() {
        var mainPage = new MainPage();
        var card = getCardWithDigitsInHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем, содержащим спецсимволы")
    void shouldShowErrorForSpecialCharsInHolder() {
        var mainPage = new MainPage();
        var card = getCardWithSpecialCharsInHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем из одного слова")
    void shouldShowErrorForOneWordHolder() {
        var mainPage = new MainPage();
        var card = getCardWithOneWordHolder();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifyNoValidationMessage();
    }

    @Test
    @DisplayName("Кредит с владельцем из одной буквы")
    void shouldShowErrorForOneCharHolder() {
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
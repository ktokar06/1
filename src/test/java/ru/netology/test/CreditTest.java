package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.netology.page.MainPage;
import ru.netology.page.CreditPage;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.netology.data.DataHelper.getApprovedCard;
import static ru.netology.data.DbUtils.getCreditStatus;
import static ru.netology.data.DbUtils.getLastTransactionId;

public class CreditTest extends BaseTest {

    @Test
    @DisplayName("Успешная покупка в кредит APPROVED картой")
    void shouldSuccessBuyWithCreditApprovedCard() {
        var mainPage = new MainPage();
        var card = getApprovedCard();

        CreditPage creditPage = mainPage.choosePaymentByCredit();
        creditPage.fillForm(card);
        creditPage.verifySuccessNotification();

        String transactionId = getLastTransactionId();
        assertThat(transactionId).isNotNull();

        String status = getCreditStatus(transactionId);
        assertThat(status).isEqualTo("APPROVED");
    }
}
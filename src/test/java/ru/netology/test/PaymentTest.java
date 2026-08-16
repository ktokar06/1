package ru.netology.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.netology.data.DataHelper.getApprovedCard;
import static ru.netology.data.DbUtils.getLastTransactionId;
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

        String transactionId = getLastTransactionId();
        assertThat(transactionId).isNotNull();

        String status = getPaymentStatus(transactionId);
        assertThat(status).isEqualTo("APPROVED");
    }
}
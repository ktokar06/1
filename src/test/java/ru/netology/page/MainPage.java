package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement buyButton = $$("button")
            .filterBy(Condition.text("Купить"))
            .first();

    private final SelenideElement buyByCreditButton = $$("button")
            .filterBy(Condition.text("Купить в кредит"))
            .first();

    public PaymentPage choosePaymentByCard() {
        buyButton.click();
        return new PaymentPage();
    }

    public CreditPage choosePaymentByCredit() {
        buyByCreditButton.click();
        return new CreditPage();
    }
}
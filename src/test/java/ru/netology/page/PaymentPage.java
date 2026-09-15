package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement holderField = $$("input").get(3);
    private final SelenideElement cvcField = $("input[placeholder='999']");
    private final SelenideElement continueButton = $$("button")
            .filterBy(Condition.exactText("Продолжить"))
            .first();

    @Step("Заполнение формы оплаты картой")
    public void fillForm(CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolder());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    @Step("Проверка сообщения об ошибке валидации: {0}")
    public void verifyValidationMessage(String expectedText) {
        $(".input__sub")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    @Step("Проверка отсутствия сообщения об ошибке валидации")
    public void verifyNoValidationMessage() {
        $$(".input__sub").filterBy(Condition.visible).shouldHave(size(0));
    }

    @Step("Проверка всплывающего сообщения: {0}")
    public void verifyNotification(String expectedText) {
        $$(".notification")
                .findBy(Condition.visible)
                .find(".notification__title")
                .shouldHave(Condition.text(expectedText), Duration.ofSeconds(15));
    }
}
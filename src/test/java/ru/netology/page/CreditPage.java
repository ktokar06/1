package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.netology.data.CardInfo;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement holderField = $$("input").get(3);
    private final SelenideElement cvcField = $("input[placeholder='999']");
    private final SelenideElement continueButton = $$("button")
            .filterBy(Condition.text("Продолжить"))
            .first();
    private final SelenideElement successNotification = $(".notification_status_ok");

    @Step("Заполнение формы кредита: номер={cardInfo.number}, месяц={cardInfo.month}, год={cardInfo.year}, держатель={cardInfo.holder}")
    public void fillForm(CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolder());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    @Step("Проверка успешного уведомления о кредите")
    public void verifySuccessNotification() {
        successNotification.shouldBe(Condition.visible)
                .shouldHave(Condition.text("Успешно"));
    }
}
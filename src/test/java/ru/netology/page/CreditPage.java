package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.netology.data.CardInfo;

import java.time.Duration;

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

    @Step("Заполнение формы кредита")
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
        successNotification.shouldHave(Condition.text("Успешно"), Duration.ofSeconds(15));
    }

    @Step("Проверка сообщения о неверном формате")
    public void verifyInvalidFormatMessage() {
        $(".input__sub")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверный формат"), Duration.ofSeconds(15));
    }

    @Step("Проверка сообщения о неверном сроке действия")
    public void verifyInvalidDateMessage() {
        $(".input__sub")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Неверно указан срок действия карты"), Duration.ofSeconds(15));
    }

    @Step("Проверка сообщения об истёкшем сроке действия")
    public void verifyExpiredDateMessage() {
        $(".input__sub")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Истёк срок действия карты"), Duration.ofSeconds(15));
    }
}
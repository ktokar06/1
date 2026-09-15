package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final String APPROVED_CARD = "1111222233334444";
    private static final String DECLINED_CARD = "5555666677778888";

    public static CardInfo getApprovedCard() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder(getValidHolder())
                .cvc("123")
                .build();
    }

    public static CardInfo getDeclinedCard() {
        return CardInfo.builder()
                .number(DECLINED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder(getValidHolder())
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithEmptyNumber() {
        return CardInfo.builder()
                .number("")
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder(getValidHolder())
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithEmptyMonth() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month("")
                .year(getFutureYear())
                .holder(getValidHolder())
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithEmptyYear() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year("")
                .holder(getValidHolder())
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithEmptyHolder() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder("")
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithEmptyCvc() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder(getValidHolder())
                .cvc("")
                .build();
    }

    public static CardInfo getCardWithExpiredMonth() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getPastMonth())
                .year(getCurrentYear())
                .holder(getValidHolder())
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithExpiredYear() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getPastYear())
                .holder(getValidHolder())
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithShortCvc() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder(getValidHolder())
                .cvc("12")
                .build();
    }

    public static CardInfo getCardWithCyrillicHolder() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder("Иван Иванов")
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithDigitsInHolder() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder("Ivan123 Ivanov")
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithSpecialCharsInHolder() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder("Ivan@#$ Ivanov")
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithOneWordHolder() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder("Ivan")
                .cvc("123")
                .build();
    }

    public static CardInfo getCardWithOneCharHolder() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
                .month(getFutureMonth())
                .year(getFutureYear())
                .holder("I")
                .cvc("123")
                .build();
    }

    private static String getFutureMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getFutureYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getPastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getPastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getValidHolder() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }
}
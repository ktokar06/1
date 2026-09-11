package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final String APPROVED_CARD = "1111222233334444";

    public static CardInfo getApprovedCard() {
        return CardInfo.builder()
                .number(APPROVED_CARD)
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
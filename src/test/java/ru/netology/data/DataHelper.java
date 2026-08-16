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

    private static String getFutureMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getFutureYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getValidHolder() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }
}
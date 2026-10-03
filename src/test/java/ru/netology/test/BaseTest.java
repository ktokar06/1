package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DbUtils;

import static com.codeborne.selenide.Selenide.open;

public abstract class BaseTest {

    @BeforeEach
    void setUp() {
        DbUtils.clearDatabase();
        open("http://localhost:8080");
    }
}
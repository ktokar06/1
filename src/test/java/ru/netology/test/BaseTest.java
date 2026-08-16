package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DbUtils;

import static com.codeborne.selenide.Selenide.*;

public abstract class BaseTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 15000;
        Configuration.pollingInterval = 500;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        DbUtils.clearDatabase();
    }

    @AfterEach
    void cleanUp() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
    }
}
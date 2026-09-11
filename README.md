## Установка и настройка

### 1. Клонирование репозитория
```bash
git clone <ссылка на репозиторий>
cd <название папки>
```

### 2. Запуск СУБД (PostgreSQL) в Docker
```bash
docker-compose up
```

### 3. Запуск приложения (SUT)
Приложение находится в директории `artifacts/aqa-shop.jar`. Запустите его командой:
```bash
java -jar artifacts/aqa-shop.jar
```
Приложение будет доступно по адресу: `http://localhost:8080`

### 4. Проверка работы приложения
Откройте браузер и перейдите по адресу `http://localhost:8080`. Должна отобразиться главная страница сервиса.

---

## Запуск автотестов

### Вариант 1. Через Gradle (рекомендуемый)
```bash
./gradlew clean test
```

### Вариант 2. Через IDE
Запустите тестовый класс `PaymentTest.java` `CreditTest`

---

## Формирование и просмотр отчётов Allure

### 1. Сборка отчёта
```bash
./gradlew allureReport
```

### 2. Открытие отчёта в браузере
```bash
./gradlew allureServe
```

[![Java CI with Gradle](https://github.com/ktokar06/1/actions/workflows/gradle.yml/badge.svg)](https://github.com/ktokar06/1/actions/workflows/gradle.yml)

<img width="1911" height="926" alt="Screenshot From 2026-09-11 21-18-18" src="https://github.com/user-attachments/assets/d1be3872-0171-44c1-af66-49a9551695de" />

<img width="1911" height="926" alt="image" src="https://github.com/user-attachments/assets/04c2145a-83d5-442e-bbc7-a1e76e6ce8e7" />

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

<img width="1902" height="1010" alt="image" src="https://github.com/user-attachments/assets/8e480f48-2fe8-4168-a372-7af742994b51" />

<img width="1902" height="1010" alt="image" src="https://github.com/user-attachments/assets/6f4e590c-b217-4a0b-8a67-9e5fc1f04b64" />


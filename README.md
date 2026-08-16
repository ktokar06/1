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

<img width="2548" height="1342" alt="image" src="https://github.com/user-attachments/assets/910563bf-f586-479c-91a4-85dab48b4988" />

<img width="2548" height="1342" alt="image" src="https://github.com/user-attachments/assets/79373685-67e4-4706-baa8-6cbcd8d4cdbc" />

<img width="2548" height="1342" alt="image" src="https://github.com/user-attachments/assets/a57bf848-d6f9-474e-913c-e59d6a00a730" />

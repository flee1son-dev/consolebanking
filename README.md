# Spring Core Bank Solution

Консольное учебное банковское приложение на Java, Spring Core, Hibernate Core .

## Что умеет
- создавать пользователей;
- показывать всех пользователей и их счета;
- создавать дополнительные счета;
- пополнять и снимать деньги;
- переводить между счетами (с комиссией для разных пользователей);
- закрывать счет с переносом остатка;
- завершать работу по команде `EXIT` или при окончании ввода (EOF).

## Технологии
- Java 21
- Spring Framework 7.0.10 (`spring-context`)
- Hibernate ORM 7.14.17.Final(`hibernate-core`)
- Конфигурация через `@Configuration`, `@PropertySource`, `@Componentscan`
- Хранение данных в базе данных (`PostgreSQL`)

## Архитектура
- `User`, `Account` — ORM-модели.
- `UserService`, `AccountService` — бизнес-логика
- `UserRepository`, `AccountRepository` – логика работы базы данных
- `OperationCommand` + `ConsoleOperationType` — обработка команд (Command pattern).
- `OperationsConsoleListener` — главный цикл приложения.
- `ConsoleInput` — единая точка чтения/валидации консольного ввода.

## Команды
- `USER_CREATE`
- `SHOW_ALL_USERS`
- `ACCOUNT_CREATE`
- `ACCOUNT_DEPOSIT`
- `ACCOUNT_WITHDRAW`
- `ACCOUNT_TRANSFER`
- `ACCOUNT_CLOSE`
- `EXIT`

## Настройки
Файл: `src/main/resources/application.properties`

```properties
account.default-amount=500
account.transfer-commission=0.02
db.url = jdbc:postgresql://localhost:5432/`your_db_name`
db.username = `your_db_username`
db.password = `your_db_password`
db.dialect=`your_db_dialect`
hibernate.hbm2ddl.auto=`your_ddl`
hibernate.show_sql=`true`
hibernate.format_sql=`true`
hibernate.current_session_context_class=`thread`
```

## Запуск
1. Собрать проект:
```bash
mvn clean package
```
2. Запустить:
```bash
mvn exec:java -Dexec.mainClass="sorokin.java.course.Main"
```

Если `exec-maven-plugin` не настроен, можно запускать из IDE через класс `Main`.

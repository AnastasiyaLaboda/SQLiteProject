# SQLiteProject
1.Написать sql скрипт для создания базы данных со следующей структурой:
TABLE Users ( userId, name, address );
TABLE Accounts ( accountId, userId, balance, currency );
TABLE Transactions( transactionId, accountId, amount )

2.Написать программу на Java, предоставляющую следующую функциональность:
a)Регистрацию нового пользователя
b)Добавление аккаунта новому пользователю
c)Пополнение существующего аккаунта
d)Снятие средств с существующего аккаунта.

3.Ограничения:
a)Каждый пользователь может иметь сколько угодно аккаунтов в разных валютах
b)Пользователь может иметь только 1 аккаунт в конкретной валюте
c)Размер транзакции не может превышать 100’000’000
d)Баланс аккаунта не может быть отрицательным или превышать 2’000’000’000
e)Поле адрес является необязательном к заполнению при регистрации

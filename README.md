# Inside Task
![JAVA version](https://img.shields.io/static/v1?label=Java&message=1.8&color=orange)
>- Добавить описание и инструкцию по запуску и комментарии в коде, если изменяете формат сообщений, то подробное описание ендпоинтов и их полей.
>- Завернуть все компоненты в докер, покрыть код тестами.
>- Проект необходимо выкладывать на github и docker hub. Обязательно наличие readme-файла.
>- Порт 8080 не указывать
>- Составить запросы (curl) через терминал для проверки работоспособности вашей программы (приложить файл с запросами).

## Installation
Install from Github 
```Java
install https://github.com/dmitrishkod/test-distance-calculator/archive/refs/heads/master.zip
```
## Overview
>- В БД создать пару sql табличек со связями (foreign keys)
>- Сделать HTTP POST эндпоинт, который получает данные в json вида:
```Java
name: "имя отправителя"
password: "пароль"
```
Этот эндпоинт проверяет пароль по БД и создает jwt токен (срок действия токена и алгоритм 
подписи не принципиален, для генерации и работе с токеном можно использовать готовую
библиотечку) 

```Java
В токен записывает данные: name: "имя отправителя" и отправляет токен в ответ,
        тоже json вида:
    token: "тут сгенерированный токен"
```
Сервер слушает и отвечает в какой-нибудь эндпоинт, в него на вход поступают данные в формате json:

```Java
Сообщения клиента-пользователя:
    name:       "имя отправителя",
    message:    "текст сообщение"
```
>- В заголовках указан Bearer токен, полученный из эндпоинта выше (между Bearer и полученным токеном должно быть нижнее подчеркивание).
Проверить токен, в случае успешной проверки токена, полученное сообщение сохранить в БД.

Если пришло сообщение вида:
```Java
    name:       "имя отправителя",
    message:    "history 10"
```
>- Проверить токен, в случае успешной проверки токена отправить отправителю 10 последних сообщений из БД
 

## Tools/Libraries
>- IDEA Community Edition
>- Git
>- Maven
>- MySQL DB
>- Java 8
>- Spring Boot/Spring Security

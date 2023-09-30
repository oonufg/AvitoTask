# AvitoTask
Тестовое задание для BackEnd разработчика от Avito\
github.com/avito-tech/backend-trainee-assignment-2023

### Запуск

---
#### 1| cd Docker

#### 2| sudo docker-compose up

#### После запуска доступен по адресу:
<http://localhost:8000/api/v1/{end_point}>

# REST API
___
## SegmentService
* ### Получить все существующие сегменты
        GET: /segment
        RESPOSE:
        CODE: 200
        [
            {
                "id": {Integer value},
                "slug": {String value}
            }
        ]
* ### Создать сегмент
      POST: /segment
      {
        "slug": {String value}
      }
      RESPONSE:
        CODE: 200
* ### Создать сегмент с процент случайных людей
      POST: /segment/p
      HTTP HEADERS:
      - percent = (Integer value)
      BODY:
      {
        "slug": {String value}
      }
* ### Удалить сегмент
      DELETE: /segment
      {
        "id": {Integer value},
        "slug": {String value}
      }  
___
## UserService
* ### Создать пользователя
        POST: /user
        {
          {empty body}
        }
        RESPOSE:
        CODE: 200
* ### Удалить пользователя
        DELETE: /user/{USER_ID}
        {
          {empty body}
        }
        RESPOSE:
        CODE: 200
* ### Получить сегменты пользователя
        GET: /user/{USER_ID}
        RESPOSE:
        CODE: 200
        [
            {
                "id": {Integer value},
                "slug": {String value}
            }
        ]
* ### Получить историю сегментов пользователя
        GET: /user/{USER_ID}/h
        RESPOSE:
        CODE: 200
        [
            {
                "id": {Integer value},
                "slug": {String value}
                "action": {String value}
                "timestamp":{String value}
            }
        ]
* ### Добавить сегменты пользователю
        POST: /user/{USER_ID}/a
        [
            {
                "id": {Integer value},
                "slug": {String value}
            }
        ]
        RESPOSE:
        CODE: 200 - if all ok
        CODE: 400 - if user not have segment or segment is bad
        CODE: 404 - if user not found
* ### Удалить сегменты пользователю
        DELETE: /user/{USER_ID}/d
        [
            {
                "id": {Integer value},
                "slug": {String value}
            }
        ]
        RESPOSE:
        CODE: 200 - if all ok
        CODE: 400 - if user not have segment or segment is bad
        CODE: 404 - if user not found


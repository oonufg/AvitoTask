# AvitoTask
Тестовое задание для BackEnd разработчика от Avito
github.com/avito-tech/backend-trainee-assignment-2023

### Запуск

---
#### 1| cd Docker

#### 2| sudo docker-compose up

##### После запуска доступен по адресу:
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




















  

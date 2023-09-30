# AvitoTask
Тестовое задание для BackEnd разработчика от Avito
github.com/avito-tech/backend-trainee-assignment-2023

Запуск -->\
1| cd Docker\
2| sudo docker-compose up

После запуска доступен по адресу:\
<http://localhost:8000/api/v1/{end_point}>

# REST API
___________________

## SegmentService

POST /segment #Создать сегмент

{

       slug

}

RESPONSE:

-201,{slug}

-400


DELETE /segment: #Удалить сегмент

{

slug

}

RESPONSE:

-200,

-404


GET /segment: #Получить все существующие сегменты

RESPONSE:

-200


-----------------------
## UserService

POST /user #Создать пользователя

{

}

RESPONSE:

201,{id}


GET /user{id} #Получить сегменты пользователя

RESPONSE:

200,{[segment]}

404


POST /user{id} #Добавить сегменты пользователю

[

    {

        segments

    }

]




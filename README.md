### hits counter

### Для запуска необходимо иметь:
* Docker
* Docker-compose

### Запуск
Из корня репозитория в консоли выполнить 
`mvn clean install`
`docker-compose up -d`

### Задание. 
```
Реализовать счетчик запросов:
1. это web сервис
2. при запросе GET / возвращает статус 200 и текущее значение счетчика
3. при запросе POST / инкрементирует счетчик на 1, возвращает статус 200 и новое значение счетчика
4. при следующем запросе возвращает уже 1. Затем 2 и т.д.
5. при запросе  DELETE / сбрасывает счетчик в 0
6. при перезагрузке приложения значение счетчика не сбрасывается
7. должна быть возможность горизонтального масштабирования сервиса. несколько инстансов приложения работают с единым счетчиком
8 под нагрузкой не должно быть race condition. значение счетчика строго равно количеству запросов POST /
9. для имплементации сервиса использовать последний Spring Boot. Язык Kotlin или Java. Можно подключить любую базу данных на выбор
10. для web сервиса должен быть написан Dockerfile 
11. должен быть написан docker-compose.yml , который запускает сам сервис и БД для него
```

> P.s.
> Проверено на macOs - работает.
> Возможные проблемы на других ОС/версиях docker с определением host контейнера для БД.
> Нужно в applications.properties выставить нужный host (localhost/host.docker.internal/ и т.д.) spring.datasource.url=jdbc:postgresql://localhost:5432/test
> 
> В ветке redis реализация задания с использованием БД redis.
> Есть проблема:  при поднятии приложения через docker-compose не определяется host redis (приложение не может подключиться к redis).
> Необходимо поднять redis и postgres (можно `docker-compose up -d`) и запустить приложение "вручную" (например в Idea). Таким образом работает на моей OS.
> Либо донастроить docker-compose.yml для того, чтобы приложение могло определить host БД редиса. Мне не удалось этого добить на macOs. Возможно позже исправлю это.

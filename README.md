# wzorzec

Генерирует репозиторий, коммиты которого расположены таким образом, чтобы сложиться в заданный узор в профиле. 

![](https://gist.github.com/codeleventh/a6b2ce4bf933c1689376ed0100111e85/raw/772aac3b709f4365d334f9392553104be24e708c/wzorzec.png)

## Установка и настройка
Убедиться, что git находится через path системного окружения.  
Отредактировать wzorzec.properties, указав свои настройки (данные учетки должны соответствовать указанным в профиле 
гитхаба).  
Запустить [jar](https://github.com/codeleventh/wzorzec/releases), сообщив входной файл: `java -jar wzorzec.jar lolcat.txt`  
Вручную запушить сгенерированный репозиторий.    

## Сборка
Собрать артефакт со всеми зависимостями можно так: `mvn clean compile assembly:single` 

## TODO
- ~~Настройка через properties~~
- Cбор существующей статистики из профиля (понадобится при генерации коммитов)
- Градации цвета 
- Обновление существующего репозитория

Проект для тестирования возможностей использования базы знаний для задач университета.

## Основные задачи проекта:
- тестирование функционала языка запросов `Gremlin` и его драйвера для `Java`
- исследование возможностей отказа от жесткой схемы в графовой базе данных, используемой для консистентности данных, в сторону использования собственных проверок на сервере и структуры схемы, хранящейся в реляционном хранилище вроде `Postgres`  
- замеры скорости загрузки и скорости запросов различных графовых баз данных, реализующих интерфейс `Gremlin`

-- Database: lesson3_db

-- DROP DATABASE IF EXISTS lesson3_db;

CREATE DATABASE lesson3_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'ru_RU.UTF-8'
    LC_CTYPE = 'ru_RU.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE lesson3_db
    IS '1. Схема прав пользователи и роли. [#1093]
Задание.

Создайте sql скрипты описывающие элементы системы.

В системе должны существовать: Пользователи. Роли. Права ролей. Заявки. Комментарии Заявок. Приложенные Файлы. Состояние заявки. Категории заявки.

Это минимум 8 основных таблиц и добавочные.

Связи между таблицами.

users - role = many-to-one
role - rules = many-to-many
item - users = many-to-one
item - comments = one-to-many
item - attachs = one-to-many
item - category = many-to-one
item - state = many-to-one

Создать SQL скрипт init.sql инициализирующий создание новой базы данных.

Создать SQL скрипт create.sql создающий таблицы для хранения структуры системы заявок.

Создать SQL скрипт insert.sql заполняющий начальные данные для системы заявок.

';
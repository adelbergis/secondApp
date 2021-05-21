package ru.adelbergis.secondapp.database;

public class UserDbSchema {//Класс для описания структуры БД
    public static class UserTable{ // Класс для таблицы users
        public static final String NAME = "users"; //Название таблицы БД
        public static final class Cols{//Класс для названия колонок таблицы
            public static final String UUID = "uuid";
            public static final String USERNAME = "firstname";
            public static final String LASTNAME = "lastname";
            public static final String PHONE = "phone";
        }
    }
}
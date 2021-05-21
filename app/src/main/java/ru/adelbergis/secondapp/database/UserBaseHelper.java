package ru.adelbergis.secondapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.adelbergis.secondapp.database.UserDbSchema.UserTable;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;//Версия БД
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Если базы не существует, то вызывается метод onCreate(SQLiteDatabase db)
        db.execSQL("create table "+ UserTable.NAME+ "(" +
                "_id integer primary key autoincrement, " +
                UserTable.Cols.UUID+"," +
                UserTable.Cols.FIRSTNAME+"," +
                UserTable.Cols.LASTNAME+"," +
                UserTable.Cols.PHONE+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//Если база существует и версии различаются то вызывается onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)

    }
}

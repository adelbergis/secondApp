package ru.adelbergis.secondapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import ru.adelbergis.secondapp.database.UserBaseHelper;
import ru.adelbergis.secondapp.database.UserDbSchema;

public class Users {
//    private String name;
//    private String phone;
    private static Users users;
    private SQLiteDatabase database;
    private Context context;
    private HashMap<UUID,String> userList;

    public static Users get(Context context){
        if(users == null){
            users = new Users(context);
        }
        return users;
    }

    private Users(Context context) {
        this.context = context.getApplicationContext();
        this.database = new UserBaseHelper(context).getWritableDatabase();//создали объект  database
    }

    public void addUser(User user){//Метод добавления пользователя в БД
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);
    }

    public void Delete(String userId) {
        database.delete(UserDbSchema.UserTable.NAME,"uuid = ?",new String[]{userId});
    }

    public void Update(String fitstName,String lastName,String userId,String phone) {
        ContentValues newValues = new ContentValues();
        newValues.put(UserDbSchema.UserTable.Cols.UUID, userId);
        newValues.put(UserDbSchema.UserTable.Cols.FIRSTNAME, fitstName);
        newValues.put(UserDbSchema.UserTable.Cols.LASTNAME, lastName);
        newValues.put(UserDbSchema.UserTable.Cols.PHONE, phone);

        database.update(UserDbSchema.UserTable.NAME,newValues,"uuid = ?",new String[]{userId});
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.UserTable.Cols.FIRSTNAME, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.LASTNAME, user.getUserLastName());
        values.put(UserDbSchema.UserTable.Cols.PHONE, user.getPhone());
        return values;
    }

    private UserCursorWrapper queryUsers(){
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME, null, null,null,null,null,null);
        return new UserCursorWrapper(cursor);
    }

    private UserCursorWrapper queryUser(String userId){
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME, null, "uuid = ?",new String[]{userId},null,null,null);
        return new UserCursorWrapper(cursor);
    }

    public HashMap<String,String> getUserHashMap(String userId) {
        HashMap<String,String> hashMap = new HashMap<>();
        UserCursorWrapper cursorWrapper = queryUser(userId);
        try {
            cursorWrapper.moveToFirst();//Перемещаемся к первой записи
            while (!cursorWrapper.isAfterLast()){
                //cursorWrapper.isAfterLast() - узнаем, есть ли еще записи
                User user = cursorWrapper.getUser();
                hashMap.put("uuid",user.getUuid().toString());
                hashMap.put("userName",user.getUserName());
                hashMap.put("lastName",user.getUserLastName());
                hashMap.put("phone",user.getPhone());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();//закрыли курсор
        }
        return hashMap;
    }

    public HashMap<UUID,String> getUserList() {
        userList = new HashMap<>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();//Перемещаемся к первой записи
            while (!cursorWrapper.isAfterLast()){
                //cursorWrapper.isAfterLast() - узнаем, есть ли еще записи
                User user = cursorWrapper.getUser();
                userList.put(user.getUuid(),user.getUserName()+" "+user.getUserLastName());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();//закрыли курсор
        }

//        for (int i = 0; i < 100; i++) {
//            userList.add("Человек_" + i);
//        }
        return userList;
    }
}

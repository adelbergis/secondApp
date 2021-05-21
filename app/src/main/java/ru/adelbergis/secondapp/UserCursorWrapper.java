package ru.adelbergis.secondapp;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import ru.adelbergis.secondapp.database.UserDbSchema;

public class UserCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String uuidString = getString(getColumnIndex(UserDbSchema.UserTable.Cols.UUID));
        String userName = getString(getColumnIndex(UserDbSchema.UserTable.Cols.FIRSTNAME));
        String userLastName = getString(getColumnIndex(UserDbSchema.UserTable.Cols.LASTNAME));
        String phone = getString(getColumnIndex(UserDbSchema.UserTable.Cols.PHONE));

        User user = new User(UUID.fromString(uuidString));
        user.setUserName(userName);
        user.setUserLastName(userLastName);
        user.setPhone(phone);

        return user;
    }
}

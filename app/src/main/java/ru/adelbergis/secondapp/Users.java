package ru.adelbergis.secondapp;

import java.util.ArrayList;
import java.util.List;

public class Users {
//    private String name;
//    private String phone;
    private List<String> userList;

    public List<String> getUserList() {
        userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            userList.add("Человек_" + i);
        }
        return userList;
    }
}

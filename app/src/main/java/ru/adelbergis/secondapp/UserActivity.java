package ru.adelbergis.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.UUID;

public class UserActivity extends AppCompatActivity {

    Button insertUserBtn;
    Button deletedUserBtn;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        insertUserBtn = findViewById(R.id.insertUserBtn);
        deletedUserBtn = findViewById(R.id.deletedUserBtn);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);

        Users users = Users.get(UserActivity.this);
        String result = getIntent().getStringExtra("user");


            HashMap<UUID,String> userList = users.getUserList();
        for (UUID key : userList.keySet()) {
                if (userList.get(key).equals(result)){
                    HashMap<String,String> user = users.getUserHashMap(key.toString());
                    //users.Delete(key.toString());
                    editTextName.setText(user.get("userName"));
                    editTextLastName.setText(user.get("lastName"));
                    editTextPhone.setText(user.get("phone"));
                    uuid = user.get("uuid");
                    break;
                }
        }
       if (uuid == null && uuid == "") {
           onBackPressed();
       }


        insertUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                users.Update(editTextName.getText().toString(),editTextLastName.getText().toString(),uuid,editTextPhone.getText().toString());
                onBackPressed();//возвращаемся с экрана
            }
        });
        deletedUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               users.Delete(uuid);
                onBackPressed();//возвращаемся с экрана
            }
        });
    }
}
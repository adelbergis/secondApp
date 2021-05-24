package ru.adelbergis.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Button addUser;
    List<String> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addUser = findViewById(R.id.addUser);
        Log.d("SYSTEM INFO: ", "Метод onCreate запущен");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //setLayoutManager - отвечает за отображение на экране
        //LinearLayoutManager - отображение ввиде списки друг за другом
        //MainActivity.this - текущий контекст


        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddUserActivity.class);
                startActivity(intent);
            }
        });

    }


    private void recyclewViewInit() {
        Users users = Users.get(MainActivity.this);
        HashMap userHashMapList = users.getUserList();
        userList = new ArrayList<String>();
        userHashMapList.forEach((k,v) -> userList.add(v.toString()));
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }

    //UserHolder отвечает за каждый элемент спска по отдельности
    //Именно здесь мы будем наполнять наш activity_item контентом
    private class UserHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;

        public UserHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.activity_item, viewGroup,false));
            //R.layout.activity_item - макет сам передаем
            itemTextView = itemView.findViewById(R.id.itemTextView);//itemView. - обращаемся к экземпляру по очереди
            Log.d("SYSTEM INFO: ", "Метод UserHolder запущен");
        }
        public void bind(String userName) {
            itemTextView.setText(userName);
            itemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Bundle bundle=new Bundle();
//                    bundle.putString("userName", userList.get((getPosition())));
//                    MyDialogFragment dialogFragment = new MyDialogFragment(MainActivity.this);
//                    dialogFragment.onCreateDialog(bundle).show();
                    Intent intent = new Intent(MainActivity.this,UserActivity.class);
                    intent.putExtra("user",userList.get((getPosition())));
                    startActivity(intent);
                    //Toast.makeText(MainActivity.this,"cliked" + getPosition(),Toast.LENGTH_SHORT).show();
                }
            });
            Log.d("SYSTEM INFO: ", "Метод bind запущен");
        }
    }
    //UserAdapter нужен для того, чтобы разместить элементы на RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {
        List<String> userList;

        public UserAdapter(List<String> userList) {
            this.userList = userList;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Log.d("SYSTEM INFO: ", "Метод onCreateViewHolder запущен");
            //inflater - наполнитель
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            Log.d("SYSTEM INFO: ", "Метод onBindViewHolder запущен");
            String userName = userList.get(position);

            userHolder.bind(userName);
        }

        @Override
        public int getItemCount() {
            Log.d("SYSTEM INFO: ", "Метод getItemCount() запущен");
            return userList.size();
        }

        public void removeItem(int position) {

        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        recyclewViewInit();
    }
}
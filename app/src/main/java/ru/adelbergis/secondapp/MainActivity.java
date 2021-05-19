package ru.adelbergis.secondapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SYSTEM INFO: ", "Метод onCreate запущен");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //setLayoutManager - отвечает за отображение на экране
        //LinearLayoutManager - отображение ввиде списки друг за другом
        //MainActivity.this - текущий контекст
        Users users = new Users();
        List userList = users.getUserList();
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
    }
}
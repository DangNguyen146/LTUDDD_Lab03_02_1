package com.example.lab03_2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private DbAdapter dbAdapter;
    private Cursor cursor;
    private List<String> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        for (int i = 0; i < 10; i++) {
            dbAdapter.createUser("Nguyễn Văn An " + i);
        }

        users = getData();
        showData();
    }

    private List<String> getData() {
        List<String> users = new ArrayList<>();

        cursor = dbAdapter.getAllUsers();
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DbAdapter.KEY_NAME);
            users.add(cursor.getString(index));
        }

        return users;
    }
    private void showData() {
        ListView lvUser = (ListView) findViewById(R.id.lv_user);
        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.item_user, users);
        lvUser.setAdapter(userAdapter);
    }
}
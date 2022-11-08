package com.aadProject.studentSubjectData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BatchmatesActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    DBHelper myDB;
    RecyclerView recyclerView;
    ArrayList<UserItem> allUsers;
    UserListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batchmates);

        myDB = new DBHelper(BatchmatesActivity.this);
        recyclerView = findViewById(R.id.list_view);
        allUsers = new ArrayList<>();
        adapter = new UserListAdapter(allUsers);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        displayData();
//        allUsers.addAll(myDB.getAllUsers());
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
//        getDataFromSQLite();

        TextView txtSubjects = findViewById(R.id.subjects);
        txtSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BatchmatesActivity.this, SubjectsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayData(){
        Cursor cursor = myDB.getData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
            return;
        }
        while(cursor.moveToNext()){
            UserItem userItem = new UserItem();
            String regno = cursor.getString(0);
            String pass = cursor.getString(1);
            String sub1 = cursor.getString(2);
            String sub2 = cursor.getString(3);
            String sub3 = cursor.getString(4);
            userItem.setRegno(regno);
            userItem.setPass(pass);
            userItem.setSub1(sub1);
            userItem.setSub2(sub2);
            userItem.setSub3(sub3);
            allUsers.add(userItem);
        }
    }
}

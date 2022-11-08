package com.aadProject.studentSubjectData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubjectsActivity extends AppCompatActivity {

    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects);
        myDB = new DBHelper(SubjectsActivity.this);
        SharedPreferences getShrd = getSharedPreferences("myproj",MODE_PRIVATE);
        String val = getShrd.getString("str","0");
        TextView txtsub1 = findViewById(R.id.Sub1my);
        TextView txtsub2 = findViewById(R.id.Sub2my);
        TextView txtsub3 = findViewById(R.id.Sub3my);
        Cursor cursor = myDB.getMyData(val);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            txtsub1.setText(cursor.getString(2));
            txtsub2.setText(cursor.getString(3));
            txtsub3.setText(cursor.getString(4));
        }
        TextView txtSeebatch = findViewById(R.id.seebatch);
        txtSeebatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubjectsActivity.this, BatchmatesActivity.class);
                startActivity(intent);
            }
        });
    }
}
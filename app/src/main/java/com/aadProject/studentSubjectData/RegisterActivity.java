package com.aadProject.studentSubjectData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtRegNo;
    EditText edtPass;
    EditText edtSub1;
    EditText edtSub2;
    EditText edtSub3;
    Button btnReg;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        edtRegNo = findViewById(R.id.newregno);
        edtPass = findViewById(R.id.newpassword);
        edtSub1 = findViewById(R.id.newsub1);
        edtSub2 = findViewById(R.id.newsub2);
        edtSub3 = findViewById(R.id.newsub3);
        btnReg = findViewById(R.id.finalregbutton);
        myDB = new DBHelper(this);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg = edtRegNo.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                String sub1 = edtSub1.getText().toString().trim();
                String sub2 = edtSub2.getText().toString().trim();
                String sub3 = edtSub3.getText().toString().trim();
                if(TextUtils.isEmpty(sub1))
                    sub1 = "-";
                if(TextUtils.isEmpty(sub2))
                    sub2 = "-";
                if(TextUtils.isEmpty(sub3))
                    sub3 = "-";
                if(register(reg,pass,sub1,sub2,sub3)) {
                    Toast.makeText(RegisterActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean register(String reg, String pass, String sub1, String sub2, String sub3){
        if(TextUtils.isEmpty(reg))
            return false;
        if(TextUtils.isEmpty(pass))
            return false;
        //put to db
        Boolean isValid = myDB.checkRegNo(reg);
        if(!isValid){
            Boolean isInserted = myDB.insertData(reg,pass,sub1,sub2,sub3);
            if(isInserted)
                return true;
            else
                return false;
        }
        Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
        return false;
    }
}
package com.aadProject.studentSubjectData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtRegno;
    EditText edtPass;
    Button btnLogin;
    Button btnRegister;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnLogin = findViewById(R.id.button);
        btnRegister = findViewById(R.id.regbutton);
        myDB = new DBHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtRegno = findViewById(R.id.regno);
                edtPass = findViewById(R.id.password);
                String reg = edtRegno.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if(login(reg,pass)){
                    SharedPreferences shrd = getSharedPreferences("myproj", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putString("str",reg);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, BatchmatesActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //register
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private Boolean login(String reg, String pass){
        //check for true
        if(TextUtils.isEmpty(reg)){
            Toast.makeText(this, "Enter valid registration number!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Enter valid password!", Toast.LENGTH_SHORT).show();
            return false;
        }
        Boolean isValid = myDB.checkLogin(reg,pass);
        if(isValid)
            return true;
        return false;
    }
}
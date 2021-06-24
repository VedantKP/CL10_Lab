package com.example.dbcrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText email = findViewById(R.id.txtEmail);
        EditText pass = findViewById(R.id.txtPwd);
        Button login = findViewById(R.id.btnLogin);
        Button register = findViewById(R.id.btnRegister);
        DBHelperMain db = new DBHelperMain(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] flag = {0};
                if(TextUtils.isEmpty(email.getText()))
                {
                    email.setError("Username is required");
                    flag[0] = 1;
                }
                if(TextUtils.isEmpty(pass.getText()))
                {
                    pass.setError("Password is required");
                    flag[0] = 1;
                }
                if(flag[0]==0)
                {
                    String username = email.getText().toString();
                    String password = pass.getText().toString();
                    if(db.checkUsernamePassword(username,password))
                    {
                        Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,HomePage.class);
                        intent.putExtra("Username",username);
                        intent.putExtra("Password",password);
                        startActivity(intent);
                        //finish();
                    }
                    else Toast.makeText(MainActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, ActivityPersonalInformation.class));
            }
        });
    }
}
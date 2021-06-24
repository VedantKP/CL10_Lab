package com.example.dbcrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityPersonalInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Button submitbtn = findViewById(R.id.submitRegisterBtn);
        EditText fname = findViewById(R.id.txtfname);
        EditText lname = findViewById(R.id.txtlname);
        EditText age = findViewById(R.id.txtage);
        EditText email = findViewById(R.id.txtEmail);
        EditText city = findViewById(R.id.txtcity);
        EditText pass = findViewById(R.id.txtpass);
        EditText repass = findViewById(R.id.txtrepass);
        DBHelperMain db = new DBHelperMain(this);

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                int[] flag = {0};
                if(TextUtils.isEmpty(fname.getText()))
                {
                    fname.setError("First name is required");
                    flag[0] = 1;
                }
                if(TextUtils.isEmpty(lname.getText()))
                {
                    lname.setError("Last name is required");
                    flag[0] = 1;
                }
                if(TextUtils.isEmpty(email.getText()))
                {
                    email.setError("Email is required");
                    flag[0] = 1;
                }
                if(TextUtils.isEmpty(age.getText()))
                {
                    age.setError("Age is required");
                    flag[0] = 1;
                }
                if(TextUtils.isEmpty(city.getText()))
                {
                    city.setError("City is required");
                    flag[0] = 1;
                }
                if(TextUtils.isEmpty(pass.getText()))
                {
                    pass.setError("City is required");
                    flag[0] = 1;
                }
                if(TextUtils.isEmpty(repass.getText()))
                {
                    repass.setError("City is required");
                    flag[0] = 1;
                }
                if(flag[0]==0)
                {
                    String username = email.getText().toString();
                    if(!db.checkUsername(username))
                    {
                        String password = pass.getText().toString();
                        String repassword = repass.getText().toString();
                        if(password.equals(repassword))
                        {
                            String strAge = age.getText().toString();
                            int intAge = 0;
                            try {
                                intAge = Integer.parseInt(strAge);
                            }
                            catch(NumberFormatException ex)
                            {
                                Toast.makeText(ActivityPersonalInformation.this,"Enter valid age",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            String strFName = fname.getText().toString();
                            String strLName = lname.getText().toString();
                            String strCity = city.getText().toString();
                            if(db.insertDataIntoDb(username,password,strFName,strLName,intAge,strCity))
                            {
                                Toast.makeText(ActivityPersonalInformation.this,"Registration successful!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ActivityPersonalInformation.this,MainActivity.class));
                                //finish();
                            }
                            else
                                Toast.makeText(ActivityPersonalInformation.this,"Registration unsuccessful!",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(ActivityPersonalInformation.this,"Passwords don't match!",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(ActivityPersonalInformation.this,"User already exists!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
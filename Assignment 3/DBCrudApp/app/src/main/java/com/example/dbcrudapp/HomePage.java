package com.example.dbcrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        EditText fname = findViewById(R.id.hometxtfname);
        EditText lname = findViewById(R.id.hometxtlname);
        EditText age = findViewById(R.id.hometxtage);
        EditText city = findViewById(R.id.hometxtcity);

        Button viewDataBtn = findViewById(R.id.viewDataBtn);
        Button updateDataBtn = findViewById(R.id.updateDataBtn);
        Button deleteAccountBtn = findViewById(R.id.deleteAccountBtn);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("Username");
        String password = extras.getString("Password");
        System.out.println("Username is => " + username);
        System.out.println("Password is => " + password);

        Toast.makeText(HomePage.this,"Welcome to the app " + username,Toast.LENGTH_SHORT).show();

        DBHelperMain db = new DBHelperMain(this);

        viewDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Cursor resultSet = db.viewData(username);
                System.out.println("Result set count => " + resultSet.getCount());
                if(resultSet.getCount()==0)
                    Toast.makeText(HomePage.this, "No Data!", Toast.LENGTH_SHORT).show();
                else
                {
                    String strFName = resultSet.getString(2);
                    String strLName = resultSet.getString(3);
                    int intAge = resultSet.getInt(4);
                    String strCity = resultSet.getString(5);
                    String strAge = String.valueOf(intAge);
                    System.out.println("First Name is => " + strFName);
                    System.out.println("Last Name is => " + strLName);
                    System.out.println("Age is => " + strAge);
                    System.out.println("City is => " + strCity);
                    fname.setText(strFName, TextView.BufferType.EDITABLE);
                    lname.setText(strLName, TextView.BufferType.EDITABLE);
                    age.setText(strAge, TextView.BufferType.EDITABLE);
                    city.setText(strCity, TextView.BufferType.EDITABLE);
                }
            }
        });

        updateDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
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
                if(flag[0]==0)
                {
                    String firstName = fname.getText().toString();
                    String lastName = fname.getText().toString();
                    String strAge = fname.getText().toString();
                    String city = fname.getText().toString();
                    int intAge = 0;
                    //try {
                    intAge = Integer.parseInt(strAge);
                    //}
                    //catch(NumberFormatException ex)
                    //{
                    //    Toast.makeText(HomePage.this,"Improper Age Value!",Toast.LENGTH_SHORT).show();
                    //    return;
                    //}
                    if(db.updateData(username,password,firstName,lastName,intAge,city))
                        Toast.makeText(HomePage.this,"Updated Successfully!",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(HomePage.this,"Could not update data!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.deleteData(username))
                {
                    Toast.makeText(HomePage.this, "Account deleted!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HomePage.this,MainActivity.class));
                    //finish();
                }
                else
                    Toast.makeText(HomePage.this,"Account could not be deleted!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
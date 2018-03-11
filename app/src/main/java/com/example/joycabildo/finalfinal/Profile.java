package com.example.joycabildo.finalfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String contactno = getIntent().getStringExtra("contactno");
        String department = getIntent().getStringExtra("department");
        String position = getIntent().getStringExtra("position");

        EditText name_et = (EditText)findViewById(R.id.name);
        name_et.setText(name);
        EditText email_et = (EditText)findViewById(R.id.email);
        email_et.setText(email);
        EditText contact_et = (EditText)findViewById(R.id.contact);
        contact_et.setText(contactno);
        EditText department_et = (EditText)findViewById(R.id.department);
        department_et.setText(department);
        EditText position_et = (EditText)findViewById(R.id.position);
        position_et.setText(position);
    }
}

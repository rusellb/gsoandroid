package com.example.joycabildo.finalfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class item_det extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_det);
        String dept_id = getIntent().getStringExtra("dept_id");
        String rcc = getIntent().getStringExtra("rcc");
        String department = getIntent().getStringExtra("department");
        EditText editText6 = (EditText)findViewById(R.id.editText6);
        editText6.setText(dept_id);
        EditText editText7 = (EditText)findViewById(R.id.editText7);
        editText7.setText(rcc);
        EditText editText16 = (EditText)findViewById(R.id.editText16);
        editText16.setText(department);
    }
}

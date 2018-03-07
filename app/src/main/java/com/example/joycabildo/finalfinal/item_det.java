package com.example.joycabildo.finalfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class item_det extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_det);
        String data = getIntent().getStringExtra("url");
        EditText editText = (EditText)findViewById(R.id.editText6);
        editText.setText(data);
    }
}

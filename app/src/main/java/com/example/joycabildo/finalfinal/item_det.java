package com.example.joycabildo.finalfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class item_det extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_det);

        String date = getIntent().getStringExtra("date");
        String item = getIntent().getStringExtra("item");
        String description = getIntent().getStringExtra("description");
        String unit = getIntent().getStringExtra("unit");
        EditText editText6 = (EditText)findViewById(R.id.hist);
        editText6.setText(date);
        EditText editText7 = (EditText)findViewById(R.id.editText7);
        editText7.setText(item);
        EditText editText18 = (EditText)findViewById(R.id.editText18);
        editText18.setText(description);
        EditText editText19 = (EditText)findViewById(R.id.editText19);
        editText19.setText(unit);
    }
}

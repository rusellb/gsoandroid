package com.example.joycabildo.finalfinal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class History_item_det extends AppCompatActivity {

    Database database = new Database(this);
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_item_det);

        int id = getIntent().getIntExtra("id", 0);
        data = database.getData(1);

        String itemName = data.getString(data.getColumnIndex("itemname"));

        EditText editText6 = (EditText)findViewById(R.id.editText6);
        editText6.setText(itemName);
        /*EditText editText17 = (EditText)findViewById(R.id.editText17);
        editText17.setText(date);
        EditText editText21 = (EditText)findViewById(R.id.editText21);
        editText21.setText(date);*/
    }
}

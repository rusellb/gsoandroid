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
        data = database.getData(id);
        data.moveToFirst();

        String itemName = data.getString(data.getColumnIndex("itemname"));
        String unitCost = data.getString(data.getColumnIndex("unitcost"));
        String description = data.getString(data.getColumnIndex("description"));

        EditText hist = (EditText)findViewById(R.id.hist);
        hist.setText(itemName);
        EditText editText17 = (EditText)findViewById(R.id.editText17);
        editText17.setText(unitCost);
        EditText editText21 = (EditText)findViewById(R.id.editText21);
        editText21.setText(description);
    }
}

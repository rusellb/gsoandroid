package com.example.joycabildo.finalfinal;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private ListView lv;
    Database database = new Database(this);
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //setContentView(R.layout.activity_custom_list);

        data = database.getAllItemDetails();

        String[] from = new String[] {Database.ITEMDETAIL_COLUMN_ITEMNAME, Database.ITEMDETAIL_COLUMN_DATETIME};
        int[] to = new int[] {R.id.name, R.id.date};
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(this, R.layout.activity_custom_list, data, from, to, 0);
        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent i = new Intent(getApplicationContext(), History_item_det.class);
                //startActivity(i);
                Toast.makeText(view.getContext(), "temp", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.joycabildo.finalfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class Menu extends AppCompatActivity {

    private CardView scan_btn;
    private CardView profile_btn;
    private CardView history_btn;
    private CardView settings_btn;
    private CardView logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Activity activity = this;
        final Context context = this;

        scan_btn = (CardView) findViewById(R.id.scan_btn);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        profile_btn = (CardView) findViewById(R.id.profile_btn);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Profile.class);
                startActivity(intent);
            }
        });

        history_btn = (CardView) findViewById(R.id.history_btn);
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, History.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            } else {
                String url = result.getContents();
                new Parse().execute(url);
                Intent intent = new Intent(getBaseContext(), item_det.class);
                intent.putExtra("dept_id", dept_id);
                intent.putExtra("rcc", rcc);
                intent.putExtra("department", department);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public class Parse extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            try {
                Reader read = new Reader();
                JSONArray json = read.getData(urls[0]);
                JSONObject object = json.getJSONObject(0);
                dept_id = object.getString("dept_id");
                rcc = object.getString("res_center_code");
                department = object.getString("department");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    String dept_id = "";
    String rcc = "";
    String department = "";
}

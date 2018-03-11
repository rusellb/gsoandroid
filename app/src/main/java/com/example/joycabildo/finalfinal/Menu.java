package com.example.joycabildo.finalfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Menu extends AppCompatActivity {

    private CardView scan_btn;
    private CardView profile_btn;
    private CardView history_btn;
    private CardView settings_btn;
    private CardView logout_btn;
    Database database = new Database(this);

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
                String user =  getIntent().getStringExtra("username");
                link = "http://192.168.43.8/imsqrgso/users/getmobileuser/" + user;
                new User().execute(link);
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
        logout_btn = (CardView) findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedpreferences.edit();
                //editor.clear();
                //editor.commit();
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
                url = result.getContents();
                new Parse().execute(url);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public class Parse extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            Context context = getApplicationContext();
            CharSequence text = "Fetching data";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        @Override
        protected Void doInBackground(String... urls) {
            try {
                Reader read = new Reader();
                JSONArray json = read.getData(urls[0]);
                JSONObject object = json.getJSONObject(0);
                item = object.getString("item");
                description = object.getString("description");
                unit = object.getString("unit");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = df.format(c.getTime());

            database.insertItemDetail(date, item, "dummy", "dummy", description, unit, "dummy", "dummy", url);

            Intent intent = new Intent(getBaseContext(), item_det.class);
            intent.putExtra("date", date);
            intent.putExtra("item", item);
            intent.putExtra("description", description);
            intent.putExtra("unit", unit);
            startActivity(intent);
        }
    }
    String item = "";
    String description = "";
    String unit = "";
    String url = "";

    public class User extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            Context context = getApplicationContext();
            CharSequence text = "Fetching data";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        @Override
        protected Void doInBackground(String... urls) {
            try {
                Reader read = new Reader();
                JSONArray json = read.getData(urls[0]);
                JSONObject object = json.getJSONObject(0);
                name = object.getString("name");
                email = object.getString("email");
                contact = object.getString("contactno");
                department = object.getString("department");
                position = object.getString("position");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Intent intent = new Intent(getBaseContext(), Profile.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("contactno", contact);
            intent.putExtra("department", department);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    String name = "";
    String email = "";
    String contact = "";
    String department = "";
    String position = "";
    String link = "";
}

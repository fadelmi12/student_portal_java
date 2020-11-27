package com.example.bismillah;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityEvaluasiDosen extends AppCompatActivity {
    ListView lvDosen;
    ImageView btnDosen;
    private ProgressDialog progressDialog;
    String id,idDosen,namaDosen,mkDosen;
    String url = "https://pajuts.000webhostapp.com/dosen/readdosen.php";
    String npm,idd;
    String mNamaDosen[] = {"Susilo Veri Yulianto, S.Kom., M.T."};
    String mNamaMK[] = {"Pemrograman Berbasis Mobile"};
    String mNIDN[] = {"0025078601"};
    TextView txtNpmEval, txtId;
    int images[] = {R.drawable.ic_baseline_person_24};
    ArrayList<HashMap<String, String>> JsonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi_dosen);

        lvDosen = (ListView)findViewById(R.id.lvdosen);
        JsonList = new ArrayList<>();
        txtNpmEval = (TextView) findViewById(R.id.npmDosen);
        txtId = (TextView) findViewById(R.id.tvIdDosen);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_Eval");
        txtNpmEval.setText(npm);
        btnDosen = (ImageView) findViewById(R.id.fotodosen);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        id = c.getString("id");
                        idDosen = c.getString("idDosen");
                        namaDosen = c.getString("namaDosen");
                        mkDosen = c.getString("mkDosen");


                        HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("id", id);
                        resultx.put("idDosen", idDosen);
                        resultx.put("namaDosen", namaDosen);
                        resultx.put("mkDosen", mkDosen);

                        JsonList.add(resultx);


                        ListAdapter adapter = new SimpleAdapter(

                                ActivityEvaluasiDosen.this, JsonList, R.layout.listview_dosen,
                                new String[]{"id","idDosen","namaDosen","mkDosen"},
                                new int[]{R.id.tvIdDosen,R.id.tvNIDN,R.id.tvNamaDosen,R.id.tvNamaMK}


                                );

                        lvDosen.setAdapter(adapter);





                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityEvaluasiDosen.this);
        requestQueue.add(request);



    }

    public void eval2 (View view) {

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);

                        id = c.getString("id");
                        idDosen = c.getString("idDosen");
                        namaDosen = c.getString("namaDosen");
                        mkDosen = c.getString("mkDosen");


                        HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("id", id);
                        resultx.put("idDosen", idDosen);
                        resultx.put("namaDosen", namaDosen);
                        resultx.put("mkDosen", mkDosen);

                        JsonList.add(resultx);


                        Intent intent = new Intent(getApplicationContext(),ActivityEvaluasiDosen2.class);
                        intent.putExtra("npmEval2", npm);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityEvaluasiDosen.this);
        requestQueue.add(request);


    }
}
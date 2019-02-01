package com.hipl.placesautocomplete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import mabbas007.tagsedittext.TagsEditText;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_main;
    private EditText et_zip;
    private ArrayList<CityData.PostOffice> mdata;
    private TagsEditText selZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_main = findViewById(R.id.rv_main);
        et_zip = findViewById(R.id.et_zip);
        selZip = findViewById(R.id.tagsEditText);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_main.setLayoutManager(llm);
        rv_main.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), rv_main,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {
                        /*Toast.makeText(MainActivity.this, "" + mdata.get(position).getName(), Toast.LENGTH_SHORT).show();*/
                        selZip.setText(mdata.get(position).getName());
                    }

                    public void onLongClickItem(View v, int position) {
                        System.out.println("On Long Click Item interface");
                    }
                }));

        et_zip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (input.isEmpty() || input.length() == 6) {
                    findFromZip(input);
                } else {
                    rv_main.setAdapter(null);
                }
            }
        });
    }

    public void findFromZip(String zip) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://postalpincode.in/api/pincode/" + zip;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!response.isEmpty()) {
                            Gson gson = new Gson();
                            CityData mydata = gson.fromJson(response, CityData.class);
                            if (!mydata.getStatus().equalsIgnoreCase("error")) {
                                mdata = mydata.getPostOffice();
                                rv_main.setAdapter(new PlacesAutoCompleteAdapter(mdata));
                            } else {
                                Toast.makeText(MainActivity.this, "" + mydata.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Data not found.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }
}

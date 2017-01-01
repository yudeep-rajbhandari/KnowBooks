package com.example.linux.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class user_final extends AppCompatActivity {

    //DatabaseHelper mydb;
    Spinner spinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_final);
        //mydb=new DatabaseHelper(this);

        final ArrayList<String>  mArrayList = new ArrayList<String>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://knowbook.herokuapp.com/information/faculty";

// Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.

                        /*for(int i = 0; i < response.length(); i++){

                            try {
                                Toast.makeText(user_final.this,response.getString("subject"),Toast.LENGTH_LONG).show();
                                mArrayList.add(response.getString("faculty"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            /*JSONObject jresponse = response.getJSONObject(i);
                            String data = jresponse.getString("faculty");*/



                    try {
                          //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, response.getJSONArray());
                        //mArrayList.add(response.getJSONArray("data")) ;
                        System.out.println("response.getString() = " + response.getJSONArray("data").getClass().getName());
                            Toast.makeText(user_final.this,"response.getString() = " + response.getJSONArray("data").getClass().getName(),Toast.LENGTH_LONG).show();

                      }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(user_final.this,"response is" +response.getString("data"), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(user_final.this,"response is" +error, Toast.LENGTH_SHORT).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);




        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.semester, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

       // DatabaseHelper mydb = new DatabaseHelper(getApplicationContext());
        //List<String> labels = mydb.getAllLabels();
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);

        // Creating adapt3r for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mArrayList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner3.setAdapter(dataAdapter);



     //loadSpinnerData();

    }

    private void loadSpinnerData() {
        DatabaseHelper mydb = new DatabaseHelper(getApplicationContext());
        List<String> labels = mydb.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner3.setAdapter(dataAdapter);
    }



}

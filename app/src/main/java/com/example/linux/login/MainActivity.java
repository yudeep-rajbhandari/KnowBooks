package com.example.linux.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private Button Login;
    int login_attempts = 5;
    DatabaseHelper mydb;
    private Button signup;
    private CallbackManager callbackManager;
    private TextView info;
    private LoginButton loginButton;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://knowbook.herokuapp.com/information/faculty";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(MainActivity.this,"response is" +response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"response is" +error, Toast.LENGTH_SHORT).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //FacebookSdk.sdkInitialize(getApplicationContext());
            callbackManager = CallbackManager.Factory.create();


            info = (TextView)findViewById(R.id.info);
            loginButton = (LoginButton)findViewById(R.id.login_button);

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Intent user_final = new Intent("com.example.linux.login.user_final");
                    startActivity(user_final);
                }

                @Override
                public void onCancel() {
                    info.setText("Login attempt cancelled.");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Login attempt failed.");
                }
            });



        onbuttonclick();
        mydb = new DatabaseHelper(this);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }







    public void onbuttonclick() {



        username = (EditText) findViewById(R.id.editText_user);
        String user = username.getText().toString();
        password = (EditText) findViewById(R.id.editText_password);
        String pass = password.getText().toString();

        Login = (Button) findViewById(R.id.button_login);
        signup = (Button) findViewById(R.id.button_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent("com.example.linux.login.signup");
                startActivity(sign);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = mydb.searchpass(username.getText().toString());

                if ((password.getText().toString()).equals(pass)) {
                    Intent user_final = new Intent("com.example.linux.login.user_final");
                    startActivity(user_final);
                } else {
                    Toast.makeText(MainActivity.this, "Username and password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





}

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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;



public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView attempts;
    private Button Login;
    int login_attempts = 5;
    DatabaseHelper mydb;
    private Button signup;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
       callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        onbuttonclick();
        mydb = new DatabaseHelper(this);

    }

   /*loginButton.registerCallback(callbackManager, new FacebookCallback() {


        @Override
        public void onSuccess(LoginResult loginResult) {

            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            System.out.print("Logged in");

        }

        @Override
        public void onCancel() {
            // App code

        }

        @Override
        public void onError(FacebookException exception) {
            // App code
            Log.i("Error" , "Error");
        }


    });
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}



*/

    public void onbuttonclick() {



        username = (EditText) findViewById(R.id.editText_user);
        String user = username.getText().toString();
        password = (EditText) findViewById(R.id.editText_password);
        String pass = password.getText().toString();
        attempts = (TextView) findViewById(R.id.textView_attempt1);
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

package com.example.linux.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    DatabaseHelper mydb;

    private EditText Name;
    private EditText password;
    private EditText Department;
    private EditText Year;
    private EditText Roll;
    private EditText Email;
    private EditText Phone;

    private Button Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name=(EditText)findViewById(R.id.editText_name);
        password=(EditText)findViewById(R.id.editText3_password);
        Department=(EditText)findViewById(R.id.editText5_department);

        Year=(EditText)findViewById(R.id.editText6_year);
        Roll=(EditText)findViewById(R.id.editText8_roll);
        Email=(EditText)findViewById(R.id.editText4_email);
        Signup=(Button)findViewById(R.id.button1_signup);
        Phone=(EditText)findViewById(R.id.editText7_phone) ;



        mydb=new DatabaseHelper(this);
        addData();
    }
public void addData(){
    Signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          boolean isInserted=  mydb.insertData(Name.getText().toString(),Department.getText().toString(),Year.getText().toString(),Roll.getText().toString(),Phone.getText().toString(),password.getText().toString(),Email.getText().toString());

        if(isInserted=true){

            Toast.makeText(signup.this,"data inserted successfully",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(signup.this,"failed to insert",Toast.LENGTH_LONG).show();
        }

        }
    });
}


}

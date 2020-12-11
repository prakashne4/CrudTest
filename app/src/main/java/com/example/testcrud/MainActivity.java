package com.example.testcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etDate, etUserName, etPassword;
    TextView txtSignup;
    Button btnLogin;
    String Date, UserName, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialzedView();
        loadValues();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
        Date = dformat.format(c.getTime());
        etDate.setText(Date);

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void loadValues() {
        Date = etDate.getText().toString();
        UserName = etUserName.getText().toString();
        Password = etPassword.getText().toString();
    }

    private void initialzedView() {
        etDate = findViewById(R.id.date);
        etUserName = findViewById(R.id.userName);
        etPassword = findViewById(R.id.password);
        txtSignup = findViewById(R.id.signup);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
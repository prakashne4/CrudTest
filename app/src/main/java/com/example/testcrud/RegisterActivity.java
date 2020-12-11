package com.example.testcrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText etId, etDate, etUserName, etPassword, etFirstName, etLastName;
    TextView txtLogin;
    Button btnSignup, btnView, btnUpdate, btnDelete;

    String Id, Date, UserName, Password, FirstName, LastName;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);

        initialzedView();

        loadValues();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
        Date = dformat.format(c.getTime());
        etDate.setText(Date);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegistration();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUser();
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

    }

    private void delete() {
        Integer deleteRows = myDb.deletData(
                Id = etId.getText().toString()
        );
        if(deleteRows > 0){
            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
           // startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }else{
            Toast.makeText(getApplicationContext(), "Not Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateData() {
        boolean isUpdated = myDb.updateData(
                Id = etId.getText().toString(),
                Date = etDate.getText().toString(),
                UserName = etUserName.getText().toString(),
                Password = etPassword.getText().toString(),
                FirstName = etFirstName.getText().toString(),
                LastName = etLastName.getText().toString()
        );
        if(isUpdated == true){
            Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
    }

    private void ViewUser() {
        Cursor res = myDb.getUser();
        if(res.getCount() == 0){
            //show message
            showMessage("Error","No Data Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id :"+res.getString(0)+"\n");
            buffer.append("Date :"+res.getString(1)+"\n");
            buffer.append("UserName :"+res.getString(2)+"\n");
            buffer.append("Password :"+res.getString(3)+"\n");
            buffer.append("FirstName :"+res.getString(4)+"\n");
            buffer.append("LastName :"+res.getString(5)+"\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    /*private void UserRegistration() {
            loadValues();
            if(UserName.equals("") || Password.equals("") || FirstName.equals("") || LastName.equals("")){
                Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
            }else{
                boolean checkUser = myDb.checkUserName(UserName);
                if(checkUser == true){
                    boolean isInserted = myDb.Register(
                            Date = etDate.getText().toString(),
                            UserName = etUserName.getText().toString(),
                            Password = etPassword.getText().toString(),
                            FirstName = etFirstName.getText().toString(),
                            LastName = etLastName.getText().toString()
                    );
                    if(isInserted == true){
                        Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "UserName Already Exist", Toast.LENGTH_SHORT).show();
                }
            }


    }*/

    private void UserRegistration() {
        UserReg userReg = new UserReg();
      //  loadValues();
        boolean isInserted = myDb.Register(
                Date = etDate.getText().toString(),
                UserName = etUserName.getText().toString(),
                Password = etPassword.getText().toString(),
                FirstName = etFirstName.getText().toString(),
                LastName = etLastName.getText().toString()
        );
        if(isInserted == true){
            Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
    }

    private void loadValues() {
        Date = etDate.getText().toString();
        UserName = etUserName.getText().toString();
        Password = etPassword.getText().toString();
        FirstName = etFirstName.getText().toString();
        LastName = etLastName.getText().toString();
    }

    private void initialzedView() {
        etId = findViewById(R.id.tv1);
        etDate = findViewById(R.id.Regdate);
        etUserName = findViewById(R.id.RegUserName);
        etPassword = findViewById(R.id.RegPassword);
        etFirstName = findViewById(R.id.RegFirstName);
        etLastName = findViewById(R.id.RegLastName);
        txtLogin = findViewById(R.id.txtLogin);
        btnSignup = findViewById(R.id.btnSignUp);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }
}
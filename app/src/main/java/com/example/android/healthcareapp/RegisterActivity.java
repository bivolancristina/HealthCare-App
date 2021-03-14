package com.example.android.healthcareapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText rFirstName,rLastName,rEmail,rPassword,rBirthDate;
    Button RegButton;
    TextView rLoginBtn;
    FirebaseAuth Auth;
    ProgressBar progressBar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rFirstName = (EditText)findViewById(R.id.first_name);
        rLastName = (EditText)findViewById(R.id.last_name);
        rEmail = (EditText)findViewById(R.id.email);
        rPassword = (EditText)findViewById(R.id.password);
        rBirthDate = (EditText)findViewById(R.id.birthdate);
        RegButton = (Button)findViewById(R.id.regbutton);
        rLoginBtn = (TextView)findViewById(R.id.alreadyreg);
        Auth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        rBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month+"/"+dayOfMonth+"/"+year;
                rBirthDate.setText(date);
            }
        };
        if(Auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        RegButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();
                String firstName = rFirstName.getText().toString().trim();
                String lastName = rLastName.getText().toString().trim();
                String birthdate = rBirthDate.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    rEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    rPassword.setError("Password is Required.");
                    return;
                }
                if(password.length() < 6) {
                    rPassword.setError("Password must be >= 6 characters.");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //register the user in firebase
                Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(
                                    firstName,lastName,email,birthdate);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        rLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    };

    @Override
    protected void onStart() {
        super.onStart();

    }
}
package com.example.foodwastagemanagmentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText email, fullName, phone, password, country;
    Button registerBtn;
    TextView loginBtn;
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Initialization
        email = findViewById(R.id.user_email);
        fullName = findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);
        phone = findViewById(R.id.user_phone);
        country = findViewById(R.id.user_country);
        registerBtn = findViewById(R.id.register_btn);
        loginBtn = findViewById(R.id.login_btn);
        mAuth = FirebaseAuth.getInstance();

        //Text


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String fullNameText = fullName.getText().toString();
                String passwordText = password.getText().toString();
                String phoneText = phone.getText().toString();
                String countryText = country.getText().toString();

                String allText[] = {
                        emailText,
                        fullNameText,
                        passwordText,
                        phoneText,
                        countryText
                };

                EditText allEditText[] ={
                        email,
                        fullName,
                        password,
                        phone,
                        country
                };

                if(emailText.isEmpty() || fullNameText.isEmpty() || passwordText.isEmpty() || phoneText.isEmpty() || countryText.isEmpty()) {
                    Utils utils = new Utils();
                    utils.validateAllEditText(allEditText, allText);
                }else {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                User u = new User(phone.getText().toString(), "user", fullName.getText().toString(), country.getText().toString());
                                FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(MainActivity.this, "Registered failed. (data)",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    }
                                });
                            } else {
                                Toast.makeText(MainActivity.this, "Authentication failed." + email.getText().toString() + password.getText().toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            }
                        });
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user != null) {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }
    }
}
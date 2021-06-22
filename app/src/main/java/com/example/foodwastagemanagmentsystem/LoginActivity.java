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

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button loginBtn;
    TextView registerBtn;
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialization
        email = findViewById(R.id.user_email);
        password = findViewById(R.id.user_password);
        registerBtn = findViewById(R.id.register_btn);
        loginBtn = findViewById(R.id.login_btn);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        final Utils utils = new Utils();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText allEditText[] = {
                        email,
                        password
                };

                String allText[] = {
                        email.getText().toString(),
                        password.getText().toString()
                };
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    utils.validateAllEditText(allEditText, allText);
                } else {
                    mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Login successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            }
                        });
                }
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
package com.example.innovadiamarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;

public class LogIn extends AppCompatActivity {

    ImageView img;
    TextInputEditText email,password;
    Button btn_login;
    TextView tvsignUp,text;
    FirebaseAuth auth;
    SimpleArcLoader simpleArcLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth=FirebaseAuth.getInstance();

        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        btn_login=findViewById(R.id.btn_register);
        tvsignUp=findViewById(R.id.tvsignUp);
        simpleArcLoader=findViewById(R.id.loader);
        img=findViewById(R.id.imageView);
        text=findViewById(R.id.text);

        final FirebaseUser user = auth.getCurrentUser();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (txt_email.isEmpty() || txt_password.isEmpty()) {
                    Toast.makeText(LogIn.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if(!user.isEmailVerified()) {
                                    Toast.makeText(LogIn.this, "Kindly verify your email first!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Intent intent = new Intent(LogIn.this, Market.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(LogIn.this, "Login failed", Toast.LENGTH_SHORT).show();
                                password.setText("");

                            }
                        }
                    });
                }

            }
        });

        tvsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

}

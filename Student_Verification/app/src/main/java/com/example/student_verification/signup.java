package com.example.student_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    Button b1;
    EditText ed1,ed2,ed3;
    String s1,s2,s3;
    FirebaseAuth f1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ed1=findViewById(R.id.e1);
        ed2=findViewById(R.id.e2);
        //ed3=findViewById(R.id.e3);
        s1=ed1.getText().toString();
        s2=ed2.getText().toString();
        //s3=ed3.getText().toString();
        b1=findViewById(R.id.bt1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1.createUserWithEmailAndPassword(s1,s2).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(signup.this,MainActivity.class));
                    }
                });

            }
        });
    }
}
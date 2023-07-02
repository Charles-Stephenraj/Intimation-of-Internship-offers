package com.example.student_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class details extends AppCompatActivity {
    Button b1;
    EditText et1,et2,et3;
    DatabaseReference dr;
    String d,ex,dept,num,link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        b1=findViewById(R.id.bt1);
        et1=findViewById(R.id.e6);
        et2=findViewById(R.id.e9);
        et3=findViewById(R.id.e4);
        d=et1.getText().toString();
        ex=et2.getText().toString();
        dr= FirebaseDatabase.getInstance("https://student-verification-e3557-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        ActivityCompat.requestPermissions(details.this,new String[]{Manifest.permission.READ_SMS,Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sm = SmsManager.getDefault();
                num=et3.getText().toString();
                dept=et1.getText().toString();
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot post: snapshot.getChildren())
                        {
                            String str=post.getKey();
                            if(str.equals(dept))
                            {
                                link= (String) post.getValue();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                sm.sendTextMessage(num,null,link,null,null);
                Toast.makeText(details.this, "Message sent", Toast.LENGTH_LONG).show();
            }
        });

    }
}
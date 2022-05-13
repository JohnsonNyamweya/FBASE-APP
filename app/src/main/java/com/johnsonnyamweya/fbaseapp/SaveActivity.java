package com.johnsonnyamweya.fbaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SaveActivity extends AppCompatActivity {

    EditText name, course, email, sUrl;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        name = (EditText) findViewById(R.id.edt_name);
        course = (EditText) findViewById(R.id.edt_course);
        email = (EditText) findViewById(R.id.edt_email);
        sUrl = (EditText) findViewById(R.id.edt_image_url);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnBack = (Button) findViewById(R.id.btn_back);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void insertData(){
        Map<String, Object> map = new HashMap<>();

        map.put("name", name.getText().toString());
        map.put("course", course.getText().toString());
        map.put("email", email.getText().toString());
        map.put("surl", sUrl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SaveActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SaveActivity.this, "Error While Inserting", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        name.setText("");
        course.setText("");
        email.setText("");
        sUrl.setText("");
    }

}
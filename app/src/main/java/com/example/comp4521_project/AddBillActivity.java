package com.example.comp4521_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddBillActivity extends AppCompatActivity {

    Button btnCreateBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        btnCreateBill = (Button) findViewById(R.id.btn_create_bill);

        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Todo: Add Bill info to Bill Table in database
            }
        });
    }
}
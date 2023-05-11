package com.example.comp4521_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AddBillActivity extends AppCompatActivity {

    EditText etTitle, etTotal;
    Button btnCreateBill;

    ToggleButton tbEvenly, tbIndividually;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        btnCreateBill = (Button) findViewById(R.id.btn_create_bill);
        tbEvenly = (ToggleButton) findViewById(R.id.tb_evenly);
        tbIndividually = (ToggleButton) findViewById(R.id.tb_individually);

        tbEvenly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setBackgroundResource(R.drawable.active_toggle_button_background_with_border);
                    tbIndividually.setChecked(false);
                    tbIndividually.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
                }
            }
        });

        tbIndividually.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setBackgroundResource(R.drawable.active_toggle_button_background_with_border);
                    tbEvenly.setChecked(false);
                    tbEvenly.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gray)));
                }
            }
        });

        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Todo: Check whether all view is filled + Add Bill info to Bill Table in database

                String splitMode = tbEvenly.isChecked() ? "Evenly" : "Individually";

                Toast.makeText(getApplicationContext(), splitMode, Toast.LENGTH_SHORT).show(); // display the current state of toggle button's

                // Back to Home Activity
                finish();
            }
        });
    }
}
package com.example.medicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePasswordActivity extends AppCompatActivity {
    EditText editText, editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_password);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editText.getText().toString();
                String text2 = editText2.getText().toString();

                if(text1.equals("") || text2.equals("")){
                    //No password
                    Toast.makeText(CreatePasswordActivity.this, "No Password Entered", Toast.LENGTH_SHORT).show();
                }else {
                    if(text1.equals(text2)){
                        // Saving the password to Shared Preferences
                        SharedPreferences settings = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("password", text1);
                        editor.apply();

                        // Password is good, go to main activity
                        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(CreatePasswordActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

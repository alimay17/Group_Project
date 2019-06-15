package com.example.medicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewMed extends AppCompatActivity {

  public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
  private EditText mEditMedView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_med);

    mEditMedView = findViewById(R.id.edit_word);

    final Button button = findViewById(R.id.button_save);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent replyIntent = new Intent();
        if(TextUtils.isEmpty(mEditMedView.getText())) {
          setResult(RESULT_CANCELED, replyIntent);
        } else {
          String med = mEditMedView.getText().toString();
          replyIntent.putExtra(EXTRA_REPLY, med);
          setResult(RESULT_OK, replyIntent);
        }
        finish();
      }
    });
  }
}

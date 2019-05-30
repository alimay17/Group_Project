package com.example.teach06_threads;

import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void oddCount(View view) {

    CountOdd count = new CountOdd(this);
    Thread thread = new Thread(count);
    thread.start();
  }

  public void evenCount(View view) {
    CountEven count = new CountEven(this);
    Thread thread = new Thread(count);
    thread.start();

  }
}
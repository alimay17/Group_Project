package com.example.teach06_threads;


import android.app.Activity;
import android.widget.Toast;
import java.lang.ref.WeakReference;

public class CountOdd implements Runnable {

  private WeakReference<MainActivity> weakActivity;

  public CountOdd(MainActivity activity) {
    this.weakActivity = new WeakReference<>(activity);
  }

  @Override
  public void run() {
    int limit = 100;

    for(int i = 0; i <= limit; i++){
      if( i % 2 != 0){
        System.out.println(i + " ");

        try {
          Thread.sleep(250);
        } catch(InterruptedException e) {
          System.out.println("error");
        }
      }
    }

    final Activity mainThread = weakActivity.get();

    if(mainThread != null) {
      mainThread.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          CharSequence text = "Count Odd is done";
          int duration = Toast.LENGTH_SHORT;

          Toast toast = Toast.makeText(mainThread, text, duration);
          toast.show();
        }
      });
    }
  }// end run()
} // CountOdd()

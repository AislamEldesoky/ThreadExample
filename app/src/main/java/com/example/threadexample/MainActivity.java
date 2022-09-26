package com.example.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button buttonStartThread ;
    private volatile boolean stopThread = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartThread = findViewById(R.id.button_start_thread);
    }
    public void startThread(View v){
        stopThread = false ;
        ExampleRunnable exampleRunnable = new ExampleRunnable();
        new Thread(exampleRunnable).start();
    }

    public void stopThread(View v){
        stopThread = true ;
    }

    class ExampleRunnable implements Runnable{
        @Override
        public void run() {
            for(int i=0; i<1000; i++){
                if(stopThread)
                    return;
                if(i==5){
                  runOnUiThread(() -> buttonStartThread.setText("50%"));
                }
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


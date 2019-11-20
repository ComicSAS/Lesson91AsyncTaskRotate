package com.example.lesson91asynctaskrotate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLog";

    MyTask mt;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "create MainActivity: " + this.hashCode());

        tv = findViewById(R.id.tv);

        mt = (MyTask) getLastCustomNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        // передаем в MyTask ссылку на текущее MainActivity
        mt.link(this);

        Log.d(LOG_TAG, "create MyTask: " + mt.hashCode());
    }


    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        // удаляем из MyTask ссылку на старое MainActivity
        mt.unlink();
        return mt;
    }
}


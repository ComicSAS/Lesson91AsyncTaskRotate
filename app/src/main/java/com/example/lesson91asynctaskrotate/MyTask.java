package com.example.lesson91asynctaskrotate;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyTask extends AsyncTask<String, Integer, Void> {

    final String LOG_TAG = "myLog";

    private MainActivity activity;

    public String info;

    // получаем ссылку на MainActivity
    public void link(MainActivity mainActivity) {
        activity = mainActivity;
        activity.tv.setText(info);
    }

    // обнуляем ссылку
    public void unlink() {
        activity = null;
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            for (int i = 1; i <= 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                publishProgress(i);
                Log.d(LOG_TAG, "i = " + i + ", MyTask: " + this.hashCode()
                        + ", MainActivity: " + activity.hashCode());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        info = "i = " + values[0];
        if (activity != null)
            activity.tv.setText(info);
    }
}

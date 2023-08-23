package com.example.despensa;

import android.app.Application;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.despensa.notifications.ProductExpirationWorker;

import java.util.concurrent.TimeUnit;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                ProductExpirationWorker.class,
                1, // Intervalo de execução (em dias)
                TimeUnit.DAYS
        ).build();

        WorkManager.getInstance(this).enqueue(periodicWorkRequest);
    }
}

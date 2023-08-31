package com.example.despensa.notifications;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.despensa.managers.UserManager;
import com.example.despensa.objects.Product;

import java.util.List;

public class ProductExpirationWorker extends Worker {

    protected static final int NOTIFICATION_ID = 123;

    public ProductExpirationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Result doWork() {
        List<Product> products = UserManager.getInstance().getLogedUser().getProductsList();
        ProductExpirationNotifier.checkAndNotifyExpiration(getApplicationContext(), products);
        return Result.success();
    }
}

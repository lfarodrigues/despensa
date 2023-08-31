package com.example.despensa.notifications;

import android.Manifest;
import android.content.Context;
import android.app.NotificationManager;
import android.app.NotificationManager.Policy;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.despensa.R;
import com.example.despensa.objects.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ProductExpirationNotifier {

    private static final int NOTIFICATION_POLICY_REQUEST_CODE = 456;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void checkAndNotifyExpiration(Context context, List<Product> products) {
        for (Product product : products) {
            if (isProductNearExpiration(product)) {
                if (hasNotificationPolicyPermission(context)) {
                    showNotification(context, product.getName());
                } else {
                    // Implementar fluxo de solicitação de permissão
                    // Pode ser por meio de uma notificação, diálogo, etc.
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static boolean isProductNearExpiration(Product product) {
        LocalDate twoDaysAhead = LocalDate.now().plusDays(2);
        LocalDate expirationDate = convertToDate(product.getExpirationDate());

        if (expirationDate != null && expirationDate.isEqual(twoDaysAhead)) {
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static LocalDate convertToDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void showNotification(Context context, String productName) {
        //if (hasNotificationPolicyPermission(context)) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                    .setSmallIcon(R.drawable.ic_product_placeholder)
                    .setContentTitle("Produto Próximo do Vencimento")
                    .setContentText("O produto " + productName + " está a 2 dias do vencimento.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            notificationManager.notify(ProductExpirationWorker.NOTIFICATION_ID, builder.build());
        //} else {
            // Solicitar a permissão ao usuário (implementar esse fluxo adequadamente)
            //requestNotificationPolicyPermission(context);
        //}
    }

    private static boolean hasNotificationPolicyPermission(Context context) {
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            Policy policy = notificationManager.getNotificationPolicy();
            return policy != null && policy.priorityCategories != 0;
        }
        return false;
    }

    private static void requestNotificationPolicyPermission(Context context) {
        // Implementar o fluxo de solicitação da permissão aqui
        // Normalmente, você abriria uma tela ou um diálogo explicando a necessidade da permissão
    }
}

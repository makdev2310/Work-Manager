package Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.workmanager.MainActivity;
import com.example.workmanager.R;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import Activities.Payroll.PayrollDetail;
import Models.Payroll;

public class PushNotificationService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        // handle a notification payload.
        if (message.getNotification() != null) {
            String messageBody = message.getNotification().getBody();
            Intent intent = new Intent();
            if(message.getNotification().getChannelId().equals("bang_luong")){
                Map<String, String> payrollFromNotification = message.getData();
                intent = new Intent(this, PayrollDetail.class);
                Log.d("message2", "alo");
                try {
                    Payroll payroll = new Payroll(
                            payrollFromNotification.get("staff_id"),
                            Long.parseLong(payrollFromNotification.get("wage")),
                            payrollFromNotification.get("role"),
                            Integer.parseInt(payrollFromNotification.get("dayoff_count")),
                            payrollFromNotification.get("detail"),
                            new SimpleDateFormat("dd/MM/yyyy").parse(payrollFromNotification.get("create_at"))
                    );
                    Log.d("message2", payroll.getRole());
                    intent.putExtra("payroll_notification", payroll);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            String channelId = message.getNotification().getChannelId();
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                            .setContentTitle(message.getNotification().getTitle())
                            .setContentText(messageBody)
                            .setAutoCancel(true)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setPriority(message.getPriority());

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);

                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(0, notificationBuilder.build());
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}

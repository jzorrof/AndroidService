package demo.fanzhong.com.myservices1;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import demo.fanzhong.com.myservices1.util.Tool;

import static demo.fanzhong.com.myservices1.util.Tool.TAG;

public class MyService extends Service {
    private NotificationManager mNM;

    public MyService() {
    }

    private MyBinder mbinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tool.TAG, "process id is " + Process.myPid());
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onStartCommand~~~~");
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy~~~~");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(){
        Log.d(TAG, "onCreate~~~~");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("onCreate")  // the status text
                    .setWhen(System.currentTimeMillis())  // the time stamp
                    .setContentTitle(getText(R.string.local_service_label))  // the label of the entry
                    .setContentText("这是通知的标题")  // the contents of the entry
                    .setContentIntent(pendingIntent)  // The intent to send when the entry is clicked
                    .build();

            // Send the notification.
            startForeground(1, notification);
            //mNM.notify(R.string.local_service_label, notification);
        }
    }
}

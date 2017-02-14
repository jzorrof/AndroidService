package demo.fanzhong.com.myservices1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import demo.fanzhong.com.myservices1.util.Tool;

public class MyRemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Tool.TAG, "MyRemoteService onCreate() executed");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(Tool.TAG, "process id is " + Process.myPid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Tool.TAG, "MyRemoteService onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

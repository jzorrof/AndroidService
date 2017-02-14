package demo.fanzhong.com.myservices1;

import android.os.Binder;
import android.util.Log;

import static demo.fanzhong.com.myservices1.util.Tool.TAG;

/**
 * Created by fanzhong on 17-2-14.
 */

public class MyBinder extends Binder {

    public void startBinderTest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "startBinderTest");
            }
        });
    }
}

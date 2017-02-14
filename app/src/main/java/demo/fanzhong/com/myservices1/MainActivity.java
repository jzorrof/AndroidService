package demo.fanzhong.com.myservices1;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStart;
    private Button mStop;
    private Button mBtBinder;
    private Button mBtUnbinder;

    private MyBinder myBinder;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //myBinder = (MyBinder) iBinder;
            //myBinder.startBinderTest();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStart = (Button) findViewById(R.id.start_service);
        mStop = (Button) findViewById(R.id.stop_service);
        mBtBinder = (Button) findViewById(R.id.start_binder);
        mBtUnbinder = (Button) findViewById(R.id.stop_binder);
        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mBtBinder.setOnClickListener(this);
        mBtUnbinder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
//                Intent startIntent = new Intent(this, MyService.class);
                Intent startIntent = new Intent(this, MyRemoteService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
//                Intent stopIntent = new Intent(this, MyService.class);
                Intent stopIntent = new Intent(this, MyRemoteService.class);
                stopService(stopIntent);
                break;
            case R.id.start_binder:
//                Intent startBinder = new Intent(this, MyRemoteService.class);
                Intent startBinder = new Intent(this, MyService.class);
                bindService(startBinder, conn, BIND_AUTO_CREATE);
                break;
            case R.id.stop_binder:
                unbindService(conn);
                break;
            default:
                break;
        }
    }
}

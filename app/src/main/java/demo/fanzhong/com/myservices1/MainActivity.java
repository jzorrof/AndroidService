package demo.fanzhong.com.myservices1;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import demo.fanzhong.com.myservices1.util.Tool;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStart;
    private Button mStop;
    private Button mBtBinder;
    private Button mBtUnbinder;

    private IMyRemoteService mIMyRemoteService;

    private MyBinder myBinder;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            isBinder = true;
            //myBinder = (MyBinder) iBinder;
            //myBinder.startBinderTest();
            mIMyRemoteService = IMyRemoteService.Stub.asInterface(iBinder);

            try {
                int plus = mIMyRemoteService.plus(1, 3);
                String S = mIMyRemoteService.toUpperCase("hEllo woRLd!");
                Log.d(Tool.TAG, "plus = " + plus);
                Log.d(Tool.TAG, "S = " + S);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(Tool.TAG, "isbinder is " + isBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBinder = false;
            Log.d(Tool.TAG, "isbinder is " + isBinder);

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

    private boolean isBinder = false;
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
                if(!isBinder) {
                    Intent startBinder = new Intent(this, MyRemoteService.class);
                    //                Intent startBinder = new Intent(this, MyService.class);
                    bindService(startBinder, conn, BIND_AUTO_CREATE);
                }
                break;
            case R.id.stop_binder:
                if(isBinder){
                    unbindService(conn);
                    isBinder = false;
                }
                break;
            default:
                break;
        }
    }
}

package com.jeck.demo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.jeck.demo.MyConstants;
import com.jeck.demo.R;
import com.jeck.demo.service.MessengerService;

import java.lang.ref.WeakReference;

public class MessengerActivity extends AppCompatActivity {

    private static final String TAG = "MessengerActivity";

    private Messenger mService;

    private Messenger mMessenger = new Messenger(new MessengerHandler(this));

    private static class MessengerHandler extends Handler{

        WeakReference<Context> wf;

        public MessengerHandler(Context context){
            wf = new WeakReference<Context>(context);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstants.MSG_FROM_SERVICE:
                    Log.d(TAG,"reveive msg from Service:"+msg.getData().getString("reply"));
                    Toast.makeText(wf.get(),"reveive msg from Service:"+msg.getData().getString("reply"),Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //通过服务端回传IBinder，创建Messenger
            mService = new android.os.Messenger(service);
            //向服务端发送消息
            Message msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello,this is client.");
            msg.setData(data);
            //将客户端Messenger作为参数传递给服务端
            msg.replyTo = mMessenger;
            try {
                mService.send(msg);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}

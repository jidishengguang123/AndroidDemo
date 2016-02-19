package com.jeck.demo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.jeck.demo.MyConstants;

import java.lang.ref.WeakReference;

public class MessengerService extends Service {

    private static final String TAG = "MessagerService";

    private final Messenger mMessenger = new Messenger(new MessagerHandler(this));

    private static class MessagerHandler extends Handler{

        WeakReference<Context> wf;

        public MessagerHandler(Context context){
            wf = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MyConstants.MSG_FROM_CLIENT:
                    Log.d(TAG, "receive msg from Client:" + msg.getData().getString("msg"));
                    Toast.makeText(wf.get(),"receive msg from Client:"+msg.getData().getString("msg"),Toast.LENGTH_LONG).show();
                    //向客户端发送消息
                    Messenger client = msg.replyTo;
                    Message replayMessage = Message.obtain(null,MyConstants.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","嗯，你的消息我已经收到，稍后会回复你。");
                    replayMessage.setData(bundle);
                    try {
                        client.send(replayMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}

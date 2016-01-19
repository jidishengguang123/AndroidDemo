package com.jeck.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.jeck.demo.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-08
 * Time: 16:02
 * Description:
 */
public class RxJavaActivity extends Activity {
    private TextView mCountTextView;
    private Subscriber mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rxjava);
        mCountTextView = (TextView) findViewById(R.id.count_textView);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (!mSubscriber.isUnsubscribed()){
            mSubscriber.unsubscribe();
        }
    }

    public void count(View view) {
        //当 Observable 被订阅的时候，OnSubscribe 的 call() 方法会自动被调用
        Observable.OnSubscribe<String> onSubscribe = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i <= 100; i++) {
                    subscriber.onNext("number:" + i);
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        };

        mSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(RxJavaActivity.this, "计时时间到了！！！！", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String t) {
                mCountTextView.setText(t);
            }
        };

        Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

}

package com.jeck.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jeck.demo.R;
import com.jeck.demo.entities.Repo;
import com.jeck.demo.retrofit.service.GitHubService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-18
 * Time: 15:52
 * Description:
 */
public class RetrofitActivity extends Activity {
    public static final String TAG = RetrofitActivity.class.getSimpleName();
    private Button mButton;
    private Subscriber mSubscriber;
    private GitHubService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_retrofit);
        mButton = (Button) findViewById(R.id.button);
        RxView.clicks(mButton).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Log.d(TAG, "-------mButton click !!!!!-------");
                initData();
            }
        });

    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mService = retrofit.create(GitHubService.class);
        loadDataByCallback();
//        loadDataByRxjava();
    }

    private void loadDataByCallback(){
        Call<List<Repo>> call = mService.listRepos("octocat");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Response<List<Repo>> response) {
                List<Repo> repos = response.body();
                Log.d(TAG, repos.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    private void loadDataByRxjava(){
        Observable<List<Repo>> observable = mService.listReposObservable("octocat");
        observable.subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());

        mSubscriber = new Subscriber<List<Repo>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "-------onCompleted-------");
                Toast.makeText(RetrofitActivity.this, "onCompleted！！！！", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "e = " + e.toString());
            }

            @Override
            public void onNext(List<Repo> repos) {
                Log.d(TAG, repos.toString());
                Toast.makeText(RetrofitActivity.this, "repos = " + repos.get(0).getArchive_url(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStart() {
                super.onStart();
            }

        };
        observable.subscribe(mSubscriber);

//        observable.subscribe(new Action1<List<Repo>>() {
//            @Override
//            public void call(List<Repo> repos) {
//                Log.d(TAG, repos.toString());
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                Log.d(TAG, throwable.toString());
//            }
//        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (mSubscriber != null && !mSubscriber.isUnsubscribed()){
            mSubscriber.unsubscribe();
        }
    }
}

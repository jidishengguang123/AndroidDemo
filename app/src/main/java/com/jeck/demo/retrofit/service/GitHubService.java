package com.jeck.demo.retrofit.service;

import com.jeck.demo.entities.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-18
 * Time: 11:41
 * Description:
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<Repo>> listReposObservable(@Path("user") String user);
}

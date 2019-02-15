package com.eklukovich.githubprviewer.data.api;

import com.eklukovich.githubprviewer.data.model.PullRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService
   {
      private volatile static GithubService ourInstance;

      private final static String BASE_URL = "https://api.github.com/";

      private GithubRequestInterface requestInterface;


      public static GithubService getInstance()
         {
            if(ourInstance == null)
               {
                  ourInstance = new GithubService();
               }

            return ourInstance;
         }

      private GithubService()
         {
            Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                            .create();

            requestInterface = new Retrofit.Builder()
                                   .baseUrl(BASE_URL)
                                   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                   .addConverterFactory(GsonConverterFactory.create(gson))
                                   .build()
                                   .create(GithubRequestInterface.class);
         }







      public Single<List<PullRequest>> doPullRequestApiCall(String owner, String repo)
         {
            return requestInterface.getPullRequests(owner, repo)
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribeOn(Schedulers.io());
         }
   }

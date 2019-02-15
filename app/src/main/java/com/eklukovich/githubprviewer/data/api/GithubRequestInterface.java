package com.eklukovich.githubprviewer.data.api;

import com.eklukovich.githubprviewer.data.model.PullRequest;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubRequestInterface
   {
      @GET("repos/{owner}/{repo}/pulls")
      Single<List<PullRequest>> getPullRequests(@Path("owner") String owner, @Path("repo") String name);


//      @GET("v1/gifs/trending")
//      Single<GiphyResponse> getTrendingGIFs(@Query("api_key") String apiKey, @Query("limit") int limit, @Query("offset") int offset);
//
//      @GET("v1/gifs/search")
//      Single<GiphyResponse> searchGIFs(@Query("api_key") String apiKey, @Query("q") String searchTerm, @Query("limit") int limit, @Query("offset") int offset);
   }

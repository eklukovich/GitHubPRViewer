package com.eklukovich.githubprviewer.data.api;

import com.eklukovich.githubprviewer.data.model.PullRequest;
import com.eklukovich.githubprviewer.data.model.PullRequestFile;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubRequestInterface
   {
      @GET("repos/{owner}/{repo}/pulls")
      Single<List<PullRequest>> getPullRequests(@Path("owner") String owner, @Path("repo") String name);

      @GET("repos/{owner}/{repo}/pulls/{pr_num}/files")
      Single<List<PullRequestFile>> getPullRequestFiles(@Path("owner") String owner, @Path("repo") String name, @Path("pr_num") int pullRequestNum);
   }

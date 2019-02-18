package com.eklukovich.githubprviewer.ui.pull_request;

import com.eklukovich.githubprviewer.data.api.GithubService;
import com.eklukovich.githubprviewer.data.model.PullRequest;

import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class PullRequestViewModel extends ViewModel
   {
      private GithubService githubService = GithubService.getInstance();

      public final ObservableList<PullRequest> pullRequestsList = new ObservableArrayList<>();

      public final ObservableBoolean isLoading = new ObservableBoolean(true);


      public PullRequestViewModel()
         {
            getPullRequests();
         }

      private void getPullRequests()
         {
            githubService.doPullRequestApiCall("pomber", "git-history").subscribe(observer);
         }


      private SingleObserver<List<PullRequest>> observer = new SingleObserver<List<PullRequest>>()
         {
            @Override public void onSuccess(List<PullRequest> value)
               {
                  pullRequestsList.clear();
                  pullRequestsList.addAll(value);

                  isLoading.set(false);
               }

            @Override public void onSubscribe(Disposable d)
               {

               }

            @Override public void onError(Throwable e)
               {
                  e.printStackTrace();
               }
         };
   }

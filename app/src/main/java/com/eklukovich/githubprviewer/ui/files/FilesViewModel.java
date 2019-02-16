package com.eklukovich.githubprviewer.ui.files;

import com.eklukovich.githubprviewer.data.api.GithubService;
import com.eklukovich.githubprviewer.data.model.PullRequestFile;

import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class FilesViewModel extends ViewModel
   {
      private GithubService githubService = GithubService.getInstance();

      public final ObservableList<PullRequestFile> pullRequestFilesList = new ObservableArrayList<>();


      public FilesViewModel(int pullRequestNumber)
         {
            getPullRequestFiles(pullRequestNumber);
         }

      private void getPullRequestFiles(int pullRequestNumber)
         {
            githubService.doPullRequestApiCall("pomber", "git-history", pullRequestNumber).subscribe(observer);
         }



      private SingleObserver<List<PullRequestFile>> observer = new SingleObserver<List<PullRequestFile>>()
         {
            @Override public void onSuccess(List<PullRequestFile> value)
               {
                  pullRequestFilesList.clear();
                  pullRequestFilesList.addAll(value);
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

package com.eklukovich.githubprviewer.ui.files;

import com.eklukovich.githubprviewer.data.api.GithubService;
import com.eklukovich.githubprviewer.data.model.PullRequestFile;
import com.eklukovich.githubprviewer.utils.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class FilesViewModel extends ViewModel
   {
      private GithubService githubService = GithubService.getInstance();

      public final ObservableList<PullRequestFile> pullRequestFilesList = new ObservableArrayList<>();

      public final ObservableField<String>  pullRequestTitle = new ObservableField<>();

      public final ObservableBoolean isLoading = new ObservableBoolean(true);


      public FilesViewModel(FilesFragmentArgs args)
         {
            getPullRequestFiles(args.getPullRequestNumber());

            pullRequestTitle.set(args.getPullRequestTitle());
         }


      private void getPullRequestFiles(int pullRequestNumber)
         {
            githubService.doPullRequestApiCall(Constants.GITHUB_REPO_OWNER, Constants.GITHUB_REPO, pullRequestNumber)
                .delay(Constants.FRAGMENT_ANIMATION_TIME_MS, TimeUnit.MILLISECONDS) // add delay for fragment animation
                .subscribe(observer);
         }



      private SingleObserver<List<PullRequestFile>> observer = new SingleObserver<List<PullRequestFile>>()
         {
            @Override public void onSuccess(List<PullRequestFile> value)
               {
                  pullRequestFilesList.clear();
                  pullRequestFilesList.addAll(value);

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

package com.eklukovich.githubprviewer.ui.diff;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FileDiffViewModelFactory implements ViewModelProvider.Factory
   {
      private String patch;


      public FileDiffViewModelFactory(String patch)
         {
            this.patch = patch;
         }

      @Override
      @NonNull
      public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
         {
            return (T) new FileDiffViewModel(patch);
         }
   }

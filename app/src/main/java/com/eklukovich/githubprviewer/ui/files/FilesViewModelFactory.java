package com.eklukovich.githubprviewer.ui.files;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FilesViewModelFactory implements ViewModelProvider.Factory
   {
      private FilesFragmentArgs args;


      public FilesViewModelFactory(FilesFragmentArgs args)
         {
            this.args = args;
         }

      @Override
      @NonNull
      public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
         {
            return (T) new FilesViewModel(args);
         }
   }

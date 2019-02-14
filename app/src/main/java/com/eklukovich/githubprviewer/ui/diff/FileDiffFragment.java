package com.eklukovich.githubprviewer.ui.diff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eklukovich.githubprviewer.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class FileDiffFragment extends Fragment
   {

      private FileDiffViewModel mViewModel;

      public static FileDiffFragment newInstance()
         {
            return new FileDiffFragment();
         }

      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
         {
            return inflater.inflate(R.layout.file_diff_fragment, container, false);
         }

      @Override
      public void onActivityCreated(@Nullable Bundle savedInstanceState)
         {
            super.onActivityCreated(savedInstanceState);
            mViewModel = ViewModelProviders.of(this).get(FileDiffViewModel.class);
            // TODO: Use the ViewModel
         }

   }

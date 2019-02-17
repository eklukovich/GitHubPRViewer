package com.eklukovich.githubprviewer.ui.diff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eklukovich.githubprviewer.R;
import com.eklukovich.githubprviewer.databinding.FragmentFileDiffBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FileDiffFragment extends Fragment
   {
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
         {
            FragmentFileDiffBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_file_diff, container, false);
            View view = binding.getRoot();

            if (getArguments() != null)
               {
                  FileDiffFragmentArgs args = FileDiffFragmentArgs.fromBundle(getArguments());

                  String patch = args.getPatchData();

                  FileDiffViewModel mViewModel = ViewModelProviders.of(this, new FileDiffViewModelFactory(patch)).get(FileDiffViewModel.class);

                  binding.setViewModel(mViewModel);
               }

            initializeRecyclerView(view.findViewById(R.id.recycler_view));

            return view;
         }


      private void initializeRecyclerView(RecyclerView recyclerView)
         {
            FileDiffAdapter adapter = new FileDiffAdapter(new ArrayList<>());

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(adapter);
         }
   }

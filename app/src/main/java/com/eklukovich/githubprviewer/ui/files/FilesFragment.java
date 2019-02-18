package com.eklukovich.githubprviewer.ui.files;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eklukovich.githubprviewer.R;
import com.eklukovich.githubprviewer.databinding.FragmentFilesBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FilesFragment extends Fragment
   {
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
         {
            FragmentFilesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_files, container, false);
            View view = binding.getRoot();

            if (getArguments() != null)
               {
                  FilesFragmentArgs args = FilesFragmentArgs.fromBundle(getArguments());

                  FilesViewModel mViewModel = ViewModelProviders.of(this, new FilesViewModelFactory(args)).get(FilesViewModel.class);
                  binding.setViewModel(mViewModel);
               }

            initializeRecyclerView(view.findViewById(R.id.recycler_view));

            return view;
         }


      private void initializeRecyclerView(RecyclerView recyclerView)
         {
            PullRequestFilesAdapter adapter = new PullRequestFilesAdapter(new ArrayList<>(), (view, file) -> Navigation.findNavController(view).navigate(FilesFragmentDirections.showDiff(file.getPatch(), file.getFilename())));

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(adapter);
         }
   }

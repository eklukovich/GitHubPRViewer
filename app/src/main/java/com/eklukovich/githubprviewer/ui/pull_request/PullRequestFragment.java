package com.eklukovich.githubprviewer.ui.pull_request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eklukovich.githubprviewer.R;
import com.eklukovich.githubprviewer.databinding.FragmentPullRequestBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PullRequestFragment extends Fragment
   {
      private FragmentPullRequestBinding binding;


      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
         {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull_request, container, false);
            View view = binding.getRoot();

            //here data must be an instance of the class MarsDataProvider
            initializeRecyclerView(view.findViewById(R.id.recycler_view));

            return view;
         }

      @Override
      public void onActivityCreated(@Nullable Bundle savedInstanceState)
         {
            super.onActivityCreated(savedInstanceState);
            PullRequestViewModel mViewModel = ViewModelProviders.of(this).get(PullRequestViewModel.class);
            binding.setViewModel(mViewModel);
         }


      private void initializeRecyclerView(RecyclerView recyclerView)
         {
            PullRequestAdapter adapter = new PullRequestAdapter(new ArrayList<>(), (view, pullRequest) -> Navigation.findNavController(view).navigate(PullRequestFragmentDirections.showFiles(pullRequest.getNumber(), pullRequest.getTitle())));

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(adapter);
         }
   }

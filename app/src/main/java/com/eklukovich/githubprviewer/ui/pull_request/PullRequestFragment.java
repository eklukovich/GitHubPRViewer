package com.eklukovich.githubprviewer.ui.pull_request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eklukovich.githubprviewer.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class PullRequestFragment extends Fragment
   {

      private PullRequestViewModel mViewModel;

      public static PullRequestFragment newInstance()
         {
            return new PullRequestFragment();
         }


      @Override
      public void onActivityCreated(@Nullable Bundle savedInstanceState)
         {
            super.onActivityCreated(savedInstanceState);
            mViewModel = ViewModelProviders.of(this).get(PullRequestViewModel.class);
            // TODO: Use the ViewModel


         }


      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
         {
            View view = inflater.inflate(R.layout.pull_request_fragment, container, false);

            Button button = view.findViewById(R.id.next_button);


            button.setOnClickListener(new View.OnClickListener()
               {
                  @Override public void onClick(View view)
                     {
                        NavDirections action = PullRequestFragmentDirections.showFiles();

                        Navigation.findNavController(view).navigate(action);
                     }
               });


            return view;
         }
   }

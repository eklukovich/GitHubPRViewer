package com.eklukovich.githubprviewer.ui.files;

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

public class FilesFragment extends Fragment
   {

      private FilesViewModel mViewModel;

      public static FilesFragment newInstance()
         {
            return new FilesFragment();
         }

      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState)
         {
            View view = inflater.inflate(R.layout.files_fragment, container, false);

            Button button = view.findViewById(R.id.next_button);


            button.setOnClickListener(new View.OnClickListener()
               {
                  @Override public void onClick(View view)
                     {
                        NavDirections action = FilesFragmentDirections.showDiff();

                        Navigation.findNavController(view).navigate(action);
                     }
               });


            return view;
         }

      @Override
      public void onActivityCreated(@Nullable Bundle savedInstanceState)
         {
            super.onActivityCreated(savedInstanceState);
            mViewModel = ViewModelProviders.of(this).get(FilesViewModel.class);
            // TODO: Use the ViewModel
         }

   }

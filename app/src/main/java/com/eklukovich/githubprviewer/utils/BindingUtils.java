package com.eklukovich.githubprviewer.utils;

import com.eklukovich.githubprviewer.data.model.PullRequest;
import com.eklukovich.githubprviewer.ui.pull_request.PullRequestAdapter;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingUtils
   {
      @BindingAdapter("adapter")
      public static void addPullRequestItems(RecyclerView recyclerView, List<PullRequest> items)
         {
            PullRequestAdapter adapter = (PullRequestAdapter) recyclerView.getAdapter();
            if (adapter != null)
               {
                  adapter.setData(items);
               }
         }
   }

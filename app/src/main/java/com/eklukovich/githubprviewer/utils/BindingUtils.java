package com.eklukovich.githubprviewer.utils;

import com.eklukovich.githubprviewer.data.model.DiffDisplayItem;
import com.eklukovich.githubprviewer.data.model.PullRequest;
import com.eklukovich.githubprviewer.data.model.PullRequestFile;
import com.eklukovich.githubprviewer.ui.diff.FileDiffAdapter;
import com.eklukovich.githubprviewer.ui.files.PullRequestFilesAdapter;
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

      @BindingAdapter("adapter_files")
      public static void addPullRequestFileItems(RecyclerView recyclerView, List<PullRequestFile> items)
         {
            PullRequestFilesAdapter adapter = (PullRequestFilesAdapter) recyclerView.getAdapter();
            if (adapter != null)
               {
                  adapter.setData(items);
               }
         }


      @BindingAdapter("adapter_diff")
      public static void addFileDiffItems(RecyclerView recyclerView, List<DiffDisplayItem> items)
         {
            FileDiffAdapter adapter = (FileDiffAdapter) recyclerView.getAdapter();
            if (adapter != null)
               {
                  adapter.setData(items);
               }
         }
   }

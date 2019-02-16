package com.eklukovich.githubprviewer.ui.common;

import android.view.View;

public interface RecyclerItemClickListener<T>
   {
      void onPullRequestItemClicked(View view, T item);
   }

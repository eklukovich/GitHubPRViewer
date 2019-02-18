package com.eklukovich.githubprviewer.utils;

import com.eklukovich.githubprviewer.ui.common.BindableAdapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingUtils
   {
      @BindingAdapter("adapter")
      @SuppressWarnings("unchecked")
      public static <T> void addListItems(RecyclerView recyclerView, T items)
         {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();

            if (adapter instanceof BindableAdapter<?>)
               {
                  ((BindableAdapter<T>)adapter).setData(items);
               }
         }
   }

package com.eklukovich.githubprviewer.ui.files;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eklukovich.githubprviewer.R;
import com.eklukovich.githubprviewer.data.model.PullRequestFile;
import com.eklukovich.githubprviewer.ui.common.BindableAdapter;
import com.eklukovich.githubprviewer.ui.common.RecyclerItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class PullRequestFilesAdapter extends RecyclerView.Adapter<PullRequestFilesAdapter.ViewHolder> implements BindableAdapter<List<PullRequestFile>>
   {
      private List<PullRequestFile> pullRequestFilesList;

      private RecyclerItemClickListener<PullRequestFile> clickListener;

      PullRequestFilesAdapter(List<PullRequestFile> fileList, @NonNull RecyclerItemClickListener<PullRequestFile> clickListener)
         {
            this.pullRequestFilesList = fileList;
            this.clickListener = clickListener;
         }


      @Override
      public void setData(List<PullRequestFile> data)
         {
            this.pullRequestFilesList = data;
            notifyDataSetChanged();
         }


      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
         {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_pull_request_file, parent, false);
            return new ViewHolder(view);
         }


      @Override
      public void onBindViewHolder(@NonNull ViewHolder holder, int position)
         {
            PullRequestFile file = pullRequestFilesList.get(position);


            holder.tvFilename.setText(file.getFilename());

            String s = holder.itemView.getContext().getResources().getString(R.string.pull_request_file_changes, file.getChanges(), file.getAdditions(), file.getDeletions());
            holder.tvFileChanges.setText(Html.fromHtml(s), TextView.BufferType.SPANNABLE);


            String status = file.getStatus();

            int textColor;

            switch (status.toLowerCase())
               {
                  case "added":
                     textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.fileStatusAdded);
                     break;
                  case "deleted":
                     textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.fileStatusDeleted);
                     break;
                  default:
                     textColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.fileStatusModified);
                     break;
               }

            holder.tvFileStatus.setText(status);
            holder.tvFileStatus.setTextColor(textColor);

         }

      @Override
      public int getItemCount()
         {
            return pullRequestFilesList.size();
         }


      class ViewHolder extends RecyclerView.ViewHolder
         {
            TextView tvFilename;
            TextView tvFileChanges;
            TextView tvFileStatus;


            ViewHolder(View view)
               {
                  super(view);

                  tvFilename = view.findViewById(R.id.tv_value_filename);
                  tvFileChanges = view.findViewById(R.id.tv_value_file_changes);
                  tvFileStatus = view.findViewById(R.id.tv_value_file_status);

                  view.setOnClickListener(v -> {
                     PullRequestFile file = pullRequestFilesList.get(getAdapterPosition());
                     clickListener.onPullRequestItemClicked(v, file);
                  });
               }
         }
   }

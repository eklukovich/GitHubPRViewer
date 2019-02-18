package com.eklukovich.githubprviewer.ui.pull_request;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eklukovich.githubprviewer.R;
import com.eklukovich.githubprviewer.data.model.PullRequest;
import com.eklukovich.githubprviewer.ui.common.BindableAdapter;
import com.eklukovich.githubprviewer.ui.common.RecyclerItemClickListener;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder> implements BindableAdapter<List<PullRequest>>
   {
      private List<PullRequest> pullRequestList;

      private RecyclerItemClickListener<PullRequest> clickListener;

      PullRequestAdapter(List<PullRequest> gifList, @NonNull RecyclerItemClickListener<PullRequest> clickListener)
         {
            this.pullRequestList = gifList;
            this.clickListener = clickListener;
         }


      @Override
      public void setData(List<PullRequest> data)
         {
            this.pullRequestList = data;

            notifyDataSetChanged();
         }


      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
         {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_pull_request, parent, false);
            return new ViewHolder(view);
         }


      @Override
      public void onBindViewHolder(@NonNull ViewHolder holder, int position)
         {
            PullRequest pullRequest = pullRequestList.get(position);


            String prNumString = holder.itemView.getContext().getResources().getString(R.string.pull_request_num, pullRequest.getNumber());
            holder.tvPullRequestNumber.setText(prNumString);

            holder.tvPullRequestTitle.setText(pullRequest.getTitle());

            String niceDateStr = DateUtils.getRelativeTimeSpanString(pullRequest.getCreatedAt().getTime(), Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS).toString();

            String prDateString = holder.itemView.getContext().getResources().getString(R.string.pull_request_date, niceDateStr, pullRequest.getUser().getLogin());
            holder.tvPullRequestDate.setText(prDateString);
         }

      @Override
      public int getItemCount()
         {
            return pullRequestList.size();
         }


      public class ViewHolder extends RecyclerView.ViewHolder
         {
            TextView tvPullRequestNumber;
            TextView tvPullRequestTitle;
            TextView tvPullRequestDate;


            public ViewHolder(View view)
               {
                  super(view);

                  tvPullRequestNumber = view.findViewById(R.id.tv_value_pr_number);
                  tvPullRequestTitle = view.findViewById(R.id.tv_value_pr_title);
                  tvPullRequestDate = view.findViewById(R.id.tv_value_pr_date);

                  view.setOnClickListener(view1 -> {
                     PullRequest pr = pullRequestList.get(getAdapterPosition());

                     clickListener.onPullRequestItemClicked(view1, pr);
                  });
               }
         }
   }

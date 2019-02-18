package com.eklukovich.githubprviewer.ui.diff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eklukovich.githubprviewer.R;
import com.eklukovich.githubprviewer.data.model.DiffDisplayItem;
import com.eklukovich.githubprviewer.data.model.DiffHeader;
import com.eklukovich.githubprviewer.data.model.DiffLine;
import com.eklukovich.githubprviewer.ui.common.BindableAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FileDiffAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BindableAdapter<List<DiffDisplayItem>>
   {
      private static final int TYPE_HEADER = 0;
      private static final int TYPE_ITEM = 1;


      private List<DiffDisplayItem> diffDisplayItemList;


      FileDiffAdapter(List<DiffDisplayItem> fileDiffList)
         {
            this.diffDisplayItemList = fileDiffList;
         }


      @Override
      public void setData(List<DiffDisplayItem> data)
         {
            this.diffDisplayItemList = data;

            notifyDataSetChanged();
         }


      @NonNull
      @Override
      public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
         {
            if (viewType == TYPE_ITEM)
               {
                  return new ViewHolderLine(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_diff, parent, false));
               }
            else if (viewType == TYPE_HEADER)
               {
                  return new ViewHolderHeader(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_diff_header, parent, false));
               }

            throw new RuntimeException("The viewholder cannot be matached! " + viewType);
         }


      @Override
      public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
         {
            DiffDisplayItem item = diffDisplayItemList.get(position);

            if (item.getType() == DiffDisplayItem.ViewType.HEADER)
               {
                  ((ViewHolderHeader) holder).setData((DiffHeader)item);
               }
            else if (item.getType() == DiffDisplayItem.ViewType.LINE)
               {
                  ((ViewHolderLine) holder).setData((DiffLine)item);
               }
         }


      @Override
      public int getItemCount()
         {
            return diffDisplayItemList.size();
         }


      public int getItemViewType(int position)
         {
            if (diffDisplayItemList.get(position).getType() == DiffDisplayItem.ViewType.HEADER)
               {
                  return TYPE_HEADER;
               }

            return TYPE_ITEM;
         }


      class ViewHolderLine extends RecyclerView.ViewHolder
         {
            AppCompatTextView tvOriginalFileNum;
            AppCompatTextView tvOriginalFileLine;
            AppCompatTextView tvUpdatedFileNum;
            AppCompatTextView tvUpdatedFileLine;


            ViewHolderLine(View view)
               {
                  super(view);

                  tvOriginalFileNum = view.findViewById(R.id.tv_original_line_num);
                  tvOriginalFileLine = view.findViewById(R.id.tv_original_file_line);
                  tvUpdatedFileNum = view.findViewById(R.id.tv_update_line_num);
                  tvUpdatedFileLine = view.findViewById(R.id.tv_updated_file_line);
               }

            void setData(DiffLine diffLine)
               {
                  String originalLine = diffLine.getOriginalContent();
                  String updatedLine = diffLine.getUpdatedContent();

                  if (diffLine.getOriginalLineNumber() == -1)
                     {
                        tvOriginalFileNum.setBackgroundColor(0xFFFAFBFC);
                        tvOriginalFileLine.setBackgroundColor(0xFFFAFBFC);
                     }
                  else if (originalLine.equals(updatedLine))
                     {
                        tvOriginalFileNum.setBackgroundColor(0xFFFAFBFC);
                        tvOriginalFileLine.setBackgroundColor(0xFFFFFFFF);
                     }
                  else
                     {
                        tvOriginalFileNum.setBackgroundColor(0xFFFFDCE0);
                        tvOriginalFileLine.setBackgroundColor(0xFFFFEEF0);
                     }


                  if (diffLine.getUpdatedLineNum() == -1)
                     {
                        tvUpdatedFileNum.setBackgroundColor(0xFFFAFBFC);
                        tvUpdatedFileLine.setBackgroundColor(0xFFFAFBFC);
                     }
                  else if (originalLine.equals(updatedLine))
                     {
                        tvUpdatedFileNum.setBackgroundColor(0xFFFAFBFC);
                        tvUpdatedFileLine.setBackgroundColor(0xFFFFFFFF);
                     }
                  else
                     {
                        tvUpdatedFileNum.setBackgroundColor(0xFFCDFFD8);
                        tvUpdatedFileLine.setBackgroundColor(0xFFE6FFED);
                     }


                  tvOriginalFileNum.setTextFuture(PrecomputedTextCompat.getTextFuture(
                      getLineNumAsString(diffLine.getOriginalLineNumber()),
                      TextViewCompat.getTextMetricsParams(tvOriginalFileNum), null));

                  tvUpdatedFileNum.setTextFuture(PrecomputedTextCompat.getTextFuture(
                      getLineNumAsString(diffLine.getUpdatedLineNum()),
                      TextViewCompat.getTextMetricsParams(tvUpdatedFileNum), null));


                  tvOriginalFileLine.setTextFuture(PrecomputedTextCompat.getTextFuture(
                      diffLine.getOriginalContent(),
                      TextViewCompat.getTextMetricsParams(tvOriginalFileLine), null));

                  tvUpdatedFileLine.setTextFuture(PrecomputedTextCompat.getTextFuture(
                      diffLine.getUpdatedContent(),
                      TextViewCompat.getTextMetricsParams(tvUpdatedFileLine), null));

               }

            private String getLineNumAsString(int value)
               {
                  return value >= 0 ? Integer.toString(value) : " ";
               }
         }


      class ViewHolderHeader extends RecyclerView.ViewHolder
         {
            TextView tvHeader;

            ViewHolderHeader(View view)
               {
                  super(view);

                  tvHeader = view.findViewById(R.id.tv_header);
               }

            void setData(DiffHeader header)
               {
                  tvHeader.setText(header.getHeaderTitle());
               }
         }
   }

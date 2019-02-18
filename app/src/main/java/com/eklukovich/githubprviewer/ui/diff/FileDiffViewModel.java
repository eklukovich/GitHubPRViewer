package com.eklukovich.githubprviewer.ui.diff;

import com.eklukovich.githubprviewer.data.model.DiffDisplayItem;
import com.eklukovich.githubprviewer.data.model.DiffHeader;
import com.eklukovich.githubprviewer.data.model.DiffLine;
import com.eklukovich.githubprviewer.utils.parser.GitPatchParser;
import com.eklukovich.githubprviewer.utils.parser.Hunk;
import com.eklukovich.githubprviewer.utils.parser.Line;
import com.eklukovich.githubprviewer.utils.parser.Patch;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FileDiffViewModel extends ViewModel
   {
      public final ObservableList<DiffDisplayItem> fileDiffList = new ObservableArrayList<>();


      public FileDiffViewModel(String patchData)
         {
            createDiffDisplayList(patchData);
         }


      private void createDiffDisplayList(String patchData)
         {
            Single.fromCallable(() -> parsePatchData(patchData))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Consumer<List<DiffDisplayItem>>) fileDiffList::addAll);
         }


         private List<DiffDisplayItem> parsePatchData(String patchData)
            {
               Patch patch = GitPatchParser.parsePatchString(patchData);

               List<DiffDisplayItem> diffDisplayItems = new ArrayList<>();

               for (Hunk hunk : patch.getHunks())
                  {
                     parseHunk(hunk, diffDisplayItems);
                  }

               return diffDisplayItems;
            }


      private void parseHunk(Hunk hunk, List<DiffDisplayItem> diffDisplayItems)
         {
            int origLinePos = hunk.getOriginalFileRange().getLineStart();
            int updatedLinePos = hunk.getUpdatedFileRange().getLineStart();

            // add the header for the hunk
            diffDisplayItems.add(new DiffHeader(hunk.getStartString()));


            int lineIndex = 0;
            List<Line> lines = hunk.getLines();


            while (lineIndex < lines.size())
               {
                  Line line = lines.get(lineIndex);

                  // add the common line for both the original and updated file
                  if (line.getLineType() == Line.LineType.COMMON)
                     {
                        diffDisplayItems.add(new DiffLine(origLinePos, line.getContent(), updatedLinePos, line.getContent()));

                        origLinePos++;
                        updatedLinePos++;

                        lineIndex++;
                     }
                  else
                     {
                        List<DiffLine> tempItemList = new ArrayList<>();

                        // add all the removed lines from the original file. Put a blank placeholder for the updated file
                        while (lineIndex < lines.size() && (line = lines.get(lineIndex)).getLineType() == Line.LineType.ORIGINAL)
                           {
                              tempItemList.add(new DiffLine(origLinePos, line.getContent(), DiffLine.NO_LINE_NUM, DiffLine.NO_CONTENT));
                              origLinePos++;

                              lineIndex++;
                           }

                        int tempIndex = 0;

                        // add all the updated lines starting at the first original line by replacing the blank placeholder or creating a new line
                        while (lineIndex < lines.size() && (line = lines.get(lineIndex)).getLineType() == Line.LineType.UPDATED)
                           {
                              // update any blank placeholders
                              if (tempIndex < tempItemList.size())
                                 {
                                    tempItemList.get(tempIndex).setUpdatedFileData(updatedLinePos, line.getContent());
                                 }
                              // there are more updated lines so create the lines with original blank placeholders
                              else
                                 {
                                    tempItemList.add(new DiffLine(DiffLine.NO_LINE_NUM, DiffLine.NO_CONTENT, updatedLinePos, line.getContent()));
                                 }

                              tempIndex++;
                              updatedLinePos++;
                              lineIndex++;
                           }

                        // add the temp list to the display list
                        diffDisplayItems.addAll(tempItemList);
                     }
               }
         }
   }

package com.eklukovich.githubprviewer.utils.parser;


import java.util.ArrayList;
import java.util.List;

public class Hunk
   {
      private Range originalFileRange;

      private Range updatedFileRange;

      private String startString;

      private List<Line> lines = new ArrayList<>();


      public void setStartString(String startString)
         {
            this.startString = startString;
         }

      public Range getOriginalFileRange()
         {
            return originalFileRange;
         }


      public Range getUpdatedFileRange()
         {
            return updatedFileRange;
         }


      public List<Line> getLines()
         {
            return lines;
         }

      public String getStartString()
         {
            return startString;
         }

      public void setOriginalFileRange(Range originalFileRange)
         {
            this.originalFileRange = originalFileRange;
         }

      public void setUpdatedFileRange(Range updatedFileRange)
         {
            this.updatedFileRange = updatedFileRange;
         }
   }
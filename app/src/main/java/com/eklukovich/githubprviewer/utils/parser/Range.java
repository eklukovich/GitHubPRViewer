package com.eklukovich.githubprviewer.utils.parser;

public class Range
   {

      private final int lineStart;
      private final int lineCount;


      public Range(int lineStart, int lineCount)
         {
            this.lineStart = lineStart;
            this.lineCount = lineCount;
         }


      public int getLineStart()
         {
            return lineStart;
         }


      public int getLineCount()
         {
            return lineCount;
         }


      private int getLineEnd()
         {
            return lineStart + lineCount - 1;
         }

   }
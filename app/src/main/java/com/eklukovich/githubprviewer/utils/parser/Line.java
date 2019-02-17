package com.eklukovich.githubprviewer.utils.parser;

public class Line
   {
      public enum LineType
         {
            ORIGINAL,
            UPDATED,
            COMMON      // line that is shared between both files
         }

      private final LineType lineType;

      private final String content;


      public Line(LineType lineType, String content)
         {
            this.lineType = lineType;
            this.content = content;
         }


      public LineType getLineType()
         {
            return lineType;
         }


      public String getContent()
         {
            return content;
         }
   }
package com.eklukovich.githubprviewer.data.model;

public class DiffLine extends DiffDisplayItem
   {
      public final static String NO_CONTENT = "";
      public final static int NO_LINE_NUM = -1;

      private int originalLineNumber;
      private String originalContent;

      private int updatedLineNum;
      private String updatedContent;


      public DiffLine(int originalLineNumber, String originalContent, int updatedLineNum, String updatedContent)
         {
            super(ViewType.LINE);

            this.originalLineNumber = originalLineNumber;
            this.originalContent = originalContent;
            this.updatedLineNum = updatedLineNum;
            this.updatedContent = updatedContent;
         }


      public int getOriginalLineNumber()
         {
            return originalLineNumber;
         }

      public String getOriginalContent()
         {
            return originalContent;
         }

      public int getUpdatedLineNum()
         {
            return updatedLineNum;
         }

      public String getUpdatedContent()
         {
            return updatedContent;
         }


      public void setOriginalFileData(int lineNum, String content)
         {
            this.originalLineNumber = lineNum;
            this.originalContent = content;
         }

      public void setUpdatedFileData(int lineNum, String content)
         {
            this.updatedLineNum = lineNum;
            this.updatedContent = content;
         }
   }

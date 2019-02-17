package com.eklukovich.githubprviewer.data.model;

public class DiffHeader extends DiffDisplayItem
   {
      private String headerTitle;


      public DiffHeader(String headerTitle)
         {
            super(ViewType.HEADER);

            this.headerTitle = headerTitle;
         }

      public String getHeaderTitle()
         {
            return headerTitle;
         }
   }

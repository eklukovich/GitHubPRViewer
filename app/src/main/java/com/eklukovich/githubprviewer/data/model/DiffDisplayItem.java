package com.eklukovich.githubprviewer.data.model;

public class DiffDisplayItem
   {
      public enum ViewType
         {
            HEADER,
            LINE
         }

      private ViewType type;

      protected DiffDisplayItem(ViewType type)
         {
            this.type = type;
         }


      public ViewType getType()
         {
            return type;
         }
   }

package com.eklukovich.githubprviewer.data.model;

import com.google.gson.annotations.SerializedName;

public class PullRequestFile
   {
      @SerializedName("filename")
      private String filename;

      @SerializedName("status")
      private String status;

      @SerializedName("additions")
      private Integer additions;

      @SerializedName("deletions")
      private Integer deletions;

      @SerializedName("changes")
      private Integer changes;

      @SerializedName("patch")
      private String patch;


      public String getFilename()
         {
            return filename;
         }

      public String getStatus()
         {
            return status;
         }

      public Integer getAdditions()
         {
            return additions;
         }

      public Integer getDeletions()
         {
            return deletions;
         }

      public Integer getChanges()
         {
            return changes;
         }

      public String getPatch()
         {
            return patch;
         }
   }

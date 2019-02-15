package com.eklukovich.githubprviewer.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PullRequest
   {
      @SerializedName("number")
      private Integer number;

      @SerializedName("state")
      private String state;

      @SerializedName("locked")
      private Boolean locked;

      @SerializedName("title")
      private String title;

      @SerializedName("user")
      private User user;

      @SerializedName("body")
      private String body;

      @SerializedName("created_at")
      private Date createdAt;

      @SerializedName("updated_at")
      private Date updatedAt;


      public Integer getNumber()
         {
            return number;
         }

      public String getState()
         {
            return state;
         }

      public Boolean getLocked()
         {
            return locked;
         }

      public String getTitle()
         {
            return title;
         }

      public User getUser()
         {
            return user;
         }

      public String getBody()
         {
            return body;
         }

      public Date getCreatedAt()
         {
            return createdAt;
         }

      public Date getUpdatedAt()
         {
            return updatedAt;
         }
   }

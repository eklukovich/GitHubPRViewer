package com.eklukovich.githubprviewer.data.model;

import com.google.gson.annotations.SerializedName;

public class User
   {
      @SerializedName("login")
      private String login;

      @SerializedName("avatar_url")
      private String avatarUrl;

      @SerializedName("url")
      private String url;


      public String getLogin()
         {
            return login;
         }

      public String getAvatarUrl()
         {
            return avatarUrl;
         }

      public String getUrl()
         {
            return url;
         }
   }

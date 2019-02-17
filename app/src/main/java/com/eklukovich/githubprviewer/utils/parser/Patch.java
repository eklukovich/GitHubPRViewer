package com.eklukovich.githubprviewer.utils.parser;

import java.util.ArrayList;
import java.util.List;

public class Patch
   {
      private List<Hunk> hunks = new ArrayList<>();


      public List<Hunk> getHunks()
         {
            return hunks;
         }
      public Hunk getLatestHunk()
         {
            return hunks.get(hunks.size() - 1);
         }
   }

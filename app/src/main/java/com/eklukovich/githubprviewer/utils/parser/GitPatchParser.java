package com.eklukovich.githubprviewer.utils.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;


/*
 * Modified from: https://github.com/stkent/githubdiffparser
 */
public class GitPatchParser
   {
      private static final Pattern HUNK_START_PATTERN = Pattern.compile("^.*-([0-9]+)(?:,([0-9]+))? \\+([0-9]+)(?:,([0-9]+))?.*$");


      public static Patch parsePatchString(String patchString)
         {
            String[] lines = patchString.split("\n");

            Patch patch = new Patch();

            for(String s : lines)
               {
                  if(matchesHunkStartPattern(s))
                     {
                        parseHunkStart(patch, s);
                     }
                  else if(matchesOriginalLinePattern(s))
                     {
                        parseOriginalFileLine(patch, s);
                     }
                  else if(matchesUpdatedLinePattern( s))
                     {
                        parseUpdatedFileLine(patch, s);
                     }
                  else if(matchesCommonLinePattern(s))
                     {
                        parseCommonLine(patch, s);
                     }
               }

            return patch;
         }


      private static void parseHunkStart(Patch patch, String string)
         {
            Matcher matcher = HUNK_START_PATTERN.matcher(string);

            if (matcher.matches()) {
               String range1Start = matcher.group(1);
               String range1Count = (matcher.group(2) != null) ? matcher.group(2) : "1";
               Range fromRange = new Range(Integer.valueOf(range1Start), Integer.valueOf(range1Count));

               String range2Start = matcher.group(3);
               String range2Count = (matcher.group(4) != null) ? matcher.group(4) : "1";
               Range toRange = new Range(Integer.valueOf(range2Start), Integer.valueOf(range2Count));

               Hunk hunk = new Hunk();

               hunk.setStartString(string);

               hunk.setOriginalFileRange(fromRange);
               hunk.setUpdatedFileRange(toRange);

               patch.getHunks().add(hunk);

            } else {
               throw new IllegalStateException(String.format("No line ranges found in the following hunk start line: '%s'. Expected something " +
                                                                 "like '-1,5 +3,5'.", string));
            }
         }

      private static void parseCommonLine(Patch patch, String currentLine)
         {
            Line line = new Line(Line.LineType.COMMON, currentLine);
            patch.getLatestHunk().getLines().add(line);
         }

      private static void parseUpdatedFileLine(Patch patch, String currentLine)
         {
            Line toLine = new Line(Line.LineType.UPDATED, currentLine);
            patch.getLatestHunk().getLines().add(toLine);
         }

      private static void parseOriginalFileLine(Patch patch, String currentLine)
         {
            Line fromLine = new Line(Line.LineType.ORIGINAL, currentLine);
            patch.getLatestHunk().getLines().add(fromLine);
         }













      private static boolean matchesOriginalLinePattern(@NonNull final String line)
         {
            return line.startsWith("-");
         }

      private static boolean matchesUpdatedLinePattern(@NonNull final String line)
         {
            return line.startsWith("+");
         }

      private static boolean matchesCommonLinePattern(@NonNull final String line)
         {
            return line.startsWith(" ");
         }

      private static boolean matchesHunkStartPattern(@NonNull final String line)
         {
            return HUNK_START_PATTERN.matcher(line).matches();
         }
   }

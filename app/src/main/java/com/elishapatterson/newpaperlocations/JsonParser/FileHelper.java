package com.elishapatterson.newpaperlocations.JsonParser;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;


/**
 * File Helper Class
 * Code example from Lynda.com, David Gassner, Java Design Patterns and APIs for Android,
 * Read text files from assets
 */
public class FileHelper {

    /**
     * Reads a file from the Projects assets directory
     * @param context; # Context from MainActivity via getApplicationContext()
     * @param filename; # name of the file or directory/name of file
     * @return String of text from file or null
     */
    public static String readFileFromAssets(Context context, String filename) {

        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            reader = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(filename)));
            String line;
            while((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            return builder.toString();
        } catch (FileNotFoundException e) {
           Log.e("FileHelper", "File not found: " + e.getMessage());
        }  catch (Exception e) {
            Log.e("FileHelper", e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.e("FileHelper", e.getMessage());
                }
            }
        }

        return null;
    }
}

package com.elishapatterson.newpaperlocations.JsonParser;

import android.content.Context;


import com.elishapatterson.newpaperlocations.model.NewspaperLocationModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Parser class for Json String
 */
public class Parser {

    private ArrayList<NewspaperLocationModel> newspaperLocationModelList;

    private Context context;

    public Parser(Context context, String filename) {

        this.context = context;

        this.newspaperLocationModelList = new ArrayList<>();

        this.parseJsonString(filename);
    }

    private void parseJsonString(String filename) {

        String json = FileHelper.readFileFromAssets(this.context, filename);

        try {

            // Create JSONObject from String
            JSONObject jsonObject = new JSONObject(json);

            // Retrieve the geodata object from the main object
            JSONObject jsonGeoData = jsonObject.getJSONObject("geodata");

            // Retrieve array of locations from geodata value
            JSONArray jsonLocations = jsonGeoData.getJSONArray("locations");

            //Iterate through JSONArray of objects
            for (int counter = 0; counter < jsonLocations.length(); counter++ ) {
                JSONObject item = jsonLocations.getJSONObject(counter);

                String style = item.getString("style");
                Double latitude = item.getDouble("lat");
                Double longitude = item.getDouble("lng");
                String details = item.getString("details");

                //Create Model Object and add to ArrayList
                newspaperLocationModelList.add(new NewspaperLocationModel(style,latitude,longitude,details));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NewspaperLocationModel> getNewspaperLocationModelList() {
        return this.newspaperLocationModelList;
    }

}

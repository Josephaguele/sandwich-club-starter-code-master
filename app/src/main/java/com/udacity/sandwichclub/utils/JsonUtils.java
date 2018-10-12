package com.udacity.sandwichclub.utils;


import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            JSONObject base = new JSONObject(json);
            JSONObject name = base.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            String placeofOrigin = base.getString("placeOfOrigin");
            String description = base.getString("description");
            String image = base.getString("image");
            JSONArray ingredients = base.getJSONArray("ingredients");

            // Since @param...alsoKnownAs is a JSON Array, it needs to be converted into an ArrayList because
            // In the sandwich class, the constructor takes in an ArrayList
            ArrayList<String> cAlsoKnownAs = new ArrayList<String>();
            if (alsoKnownAs != null) {
                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    cAlsoKnownAs.add(alsoKnownAs.getString(i));
                }
            }

            // Also since @param...ingredients is a JSON array, it needs to be converted into an ArrayList
            // because of its constructor in the sandwich class is an arraylist
            ArrayList<String> cIngredients = new ArrayList<String>();
            if (ingredients != null) {
                for (int i = 0; i < ingredients.length(); i++) {
                    cIngredients.add(ingredients.getString(i));
                }
            }

            // calling the sandwich constructor so as to implement the values of the JSON parsed.
            return new Sandwich(mainName, cAlsoKnownAs, placeofOrigin, description, image, cIngredients);

        } catch (JSONException e) {
            Log.e("JsonUtils", "Problem parsing the JSON results", e);
        }
        return null;
    }
}

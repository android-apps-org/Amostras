package com.jdemaagd.clubedesanduiche.utils;

import com.jdemaagd.clubedesanduiche.models.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        JSONObject sandwichObject = new JSONObject(json);
        JSONObject nameObject = sandwichObject.getJSONObject("name");
        String mainName = nameObject.getString("mainName");
        JSONArray alsoKnownAs = nameObject.getJSONArray("alsoKnownAs");
        String placeOfOrigin = sandwichObject.getString("placeOfOrigin");
        String description = sandwichObject.getString("description");
        String imagePath = sandwichObject.getString("image");
        JSONArray ingredients = sandwichObject.optJSONArray("ingredients");

        return new Sandwich(mainName, toList(alsoKnownAs), placeOfOrigin, description, imagePath, toList(ingredients));
    }

    private static List<String> toList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}

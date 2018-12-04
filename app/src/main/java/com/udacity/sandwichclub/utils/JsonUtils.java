package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {
    private final static String TAG ="JsonUtils";
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwichObject=null ;

        try {
            ArrayList<String> alsoKnownArrayList = new ArrayList<>();
            ArrayList<String> ingredientArrayList = new ArrayList<>();
            JSONObject result = new JSONObject(json);
            Log.d(TAG,"parseSandwichJson(): Json Response: "+ result.toString());


            //Get ImageView Link
            String imageViewLink = result.getString("image");
            //Get the main of the json
            JSONObject name = result.getJSONObject("name");
            String mainName = name.getString("mainName");
            Log.d(TAG,"parseSandwichJson(): Name value is: "+ name.getString("mainName"));

            //Get the alsoKnownAs ArrayList
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
            for (int i=0;i<alsoKnownAs.length();i++){
                String temp = alsoKnownAs.getString(i);
                Log.d(TAG,"parseSandwichJson(): Value of AlsoKnownAs index: "+i +"  "+ " is "+ temp);
                alsoKnownArrayList.add(temp);
            }

            //Get Ingredient ArrayList
            JSONArray ingredientJA = result.getJSONArray("ingredients");
            for (int i=0;i<ingredientJA.length();i++){
                String temp = ingredientJA.getString(i);
                Log.d(TAG,"parseSandwichJson(): Value of Ingredients index: "+i +"  "+ " is "+ temp);
                ingredientArrayList.add(temp);
            }

            //Get Description JSONObject
            String tempDesc = result.getString("description");
            Log.d(TAG,"parseSandwichJson(): Value of Description : "+ tempDesc);

            //Get Place of Origins
            String placeOfOrgintemp = result.getString("placeOfOrigin");
            Log.d(TAG,"parseSandwichJson(): Value of Place Of Origin : "+ placeOfOrgintemp);

            sandwichObject = new Sandwich(mainName,alsoKnownArrayList,placeOfOrgintemp,tempDesc,imageViewLink,ingredientArrayList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwichObject;
    }
}

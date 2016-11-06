package com.pinterest.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pinterest.model.PhotoCategories;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by harsh on 05/11/16.
 */
public class GsonParser {
    private static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static ArrayList<PhotoCategories> parsePhotoDetails(InputStream in) {
        Type listType = new TypeToken<ArrayList<PhotoCategories>>(){}.getType();
        ArrayList<PhotoCategories> list = gson.fromJson(new InputStreamReader(in), listType);
        return list;
    }

}

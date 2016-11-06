package com.pinterest.network;

import android.content.Context;

import com.pinterest.model.PhotoCategories;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by harsh on 05/11/16.
 */
public class RequestWrapper {

    private static Context context;
    public static final String FETCH_PHOTOS = "fetchphotos";
    public static void initialize(Context appContext) {
        context = appContext;
    }
    public static InputStream fetchhttp(String urlstring) {

        try {
            Request.Builder builder = new Request.Builder().url(urlstring);
            builder = HttpManager.setHTTPHeaders(context, builder);
            Response response = HttpManager.execute(builder.build());

            int responseCode = response.code();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = getStream(response);
                return in;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getStream(Response response) throws IllegalStateException, IOException {

        InputStream instream = response.body().byteStream();

        String contentEncoding = response.header("Content-Encoding");
        if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
            instream = new GZIPInputStream(instream);
        }
        return instream;
    }

    public static Object request(String url, String objectType) {
        Object responseObject = null;
        InputStream httpresult = fetchhttp(url);
        if (httpresult != null) {
            responseObject = parse(httpresult, objectType);
        }
        if (httpresult != null) {
            try {
                httpresult.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseObject;
    }
    public static Object parse(InputStream result, String type) {
        Object o = null;
        if (result != null) {
        try{
            if(type == FETCH_PHOTOS){
                ArrayList<PhotoCategories> categories = GsonParser.parsePhotoDetails(result);
                return categories;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null)
                    result.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        }
        return o;
    }
}

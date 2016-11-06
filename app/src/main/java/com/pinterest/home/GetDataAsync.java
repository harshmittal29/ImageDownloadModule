package com.pinterest.home;

import android.content.Context;
import android.os.AsyncTask;

import com.pinterest.model.PhotoCategories;
import com.pinterest.network.RequestWrapper;

import java.util.ArrayList;

/**
 * Created by harsh on 05/11/16.
 */
public abstract class GetDataAsync extends AsyncTask <String, Void, Boolean>{

    protected ArrayList<PhotoCategories> response;
    public void fetchData(Context context){
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onStart();
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String url = "http://pastebin.com/raw/wgkJgazE";
        response = (ArrayList<PhotoCategories>) RequestWrapper.request(url, RequestWrapper.FETCH_PHOTOS);
        return response!=null && response.size()>0;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean) {
            onFinish();
        } else {
            onError();
        }
    }

    abstract void onStart();
    abstract void onFinish();
    abstract void onError();
}

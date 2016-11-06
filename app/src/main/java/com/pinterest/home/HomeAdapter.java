package com.pinterest.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinterest.R;
import com.pinterest.common.Utils;
import com.pinterest.model.PhotoCategories;

import java.util.ArrayList;

/**
 * Created by harsh on 05/11/16.
 *
 */
public class HomeAdapter extends RecyclerView.Adapter {
    public static final int TYPE_CATEGORIES = 0x1f;
    private Context context;
    private ArrayList<PhotoCategories> response;
    private ArrayList<CustomData> items = new ArrayList<>();
    private CategoryListener listener;

    public HomeAdapter(Context context, ArrayList<PhotoCategories> response) {
        this.context = context;
        this.response = response;
        listener = (CategoryListener) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.category_layout, parent, false);
        return getCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CustomData customData = items.get(position);
        switch (customData.type) {
            case TYPE_CATEGORIES:
                TagRow tagRow = (TagRow) customData.data;
                ((CategoryAdapter) holder).bind(tagRow);
                break;
        }
    }

    /**
     * set data from response and notify adapter
     */
    public void setResponse(ArrayList<PhotoCategories> response) {
        this.response = response;
        populateCustomDataSet();
    }

    /**
     * populate custom data set
     */
    private void populateCustomDataSet() {
        if (items != null)
            items.clear();
        else
            items = new ArrayList<>();

        if (response != null && response.size() > 0) {
            adCategories(response);
        }
    }

    /**
     * adding rows to recycler view
     * @param tags
     */
    private void adCategories(ArrayList<PhotoCategories> tags) {
        int i = 0;
        while (i < tags.size()) {
            TagRow tagRow = new TagRow();
            if (i < tags.size()) {
                tagRow.tagTiles.add(tags.get(i));
                i++;
            }
            if (i < tags.size()) {
                tagRow.tagTiles.add(tags.get(i));
                i++;
            }
            items.add(new CustomData(TYPE_CATEGORIES, tagRow));
        }
    }

    @NonNull
    private RecyclerView.ViewHolder getCategoryViewHolder(View view) {
        if (Utils.isAndroidL()) {
            view.setElevation(context.getResources().getDimensionPixelOffset(R.dimen.elevation_1));
        }
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = context.getResources().getDimensionPixelOffset(R.dimen.padding_10);
        params.topMargin = context.getResources().getDimensionPixelOffset(R.dimen.padding_10);
        view.setLayoutParams(params);
        return new CategoryAdapter(context, view, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class TagRow {
        public ArrayList<PhotoCategories> tagTiles = new ArrayList<>();

        public TagRow() {
        }
    }
    /**
     * static custom data class
     */
    public static class CustomData {
        public int type;
        public Object data;

        CustomData(int type, Object data) {
            this.type = type;
            this.data = data;
        }
    }
}



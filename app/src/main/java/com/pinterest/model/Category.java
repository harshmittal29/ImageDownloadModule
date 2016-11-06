package com.pinterest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by harsh on 05/11/16.
 */
public class Category implements Serializable {
    @Expose
    @SerializedName("id") String id;
    @Expose @SerializedName("title") String title;
    @Expose @SerializedName("photo_count") int photoCount;
    @Expose @SerializedName("links") Links links;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}

package com.pinterest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by harsh on 05/11/16.
 */
public class Links implements Serializable{
    @Expose
    @SerializedName("self") String self;
    @Expose @SerializedName("html") String html;
    @Expose @SerializedName("photos") String photos;
    @Expose @SerializedName("likes") String likes;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}

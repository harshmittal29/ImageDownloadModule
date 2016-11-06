package com.pinterest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by harsh on 05/11/16.
 */
public class PhotoCategories implements Serializable {

    @Expose @SerializedName("id") String id;
    @Expose @SerializedName("created_at") String createdAt;
    @Expose @SerializedName("width") int width;
    @Expose @SerializedName("height") int height;
    @Expose @SerializedName("color") String color;
    @Expose @SerializedName("likes") int likes;
    @Expose @SerializedName("liked_by_user") boolean likedByUser;
    @Expose @SerializedName("user") User user;
//    @Expose @SerializedName("current_user_collections") ArrayList<UserCollections> userCollections;
    @Expose @SerializedName("urls") Url url;
    @Expose @SerializedName("categories")ArrayList<Category> categoryList;
    @Expose @SerializedName("links") Links links;

    public PhotoCategories(){
        id = "";
        width = 0;
        height = 0;
        createdAt = "";
        color = "";
        likes = 0;
        likedByUser = false;
        user = new User();
//        userCollections = new ArrayList<>();
        url = new Url();
        categoryList = new ArrayList<>();
        links = new Links();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public ArrayList<UserCollections> getUserCollections() {
//        return userCollections;
//    }
//
//    public void setUserCollections(ArrayList<UserCollections> userCollections) {
//        this.userCollections = userCollections;
//    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}

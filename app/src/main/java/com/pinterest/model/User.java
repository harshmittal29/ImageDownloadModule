package com.pinterest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by harsh on 05/11/16.
 */
public class User implements Serializable {

    @Expose
    @SerializedName("id") String id;
    @Expose @SerializedName("username") String userName;
    @Expose @SerializedName("name") String name;
    @Expose @SerializedName("profile_image") UserProfileImage profileImage;
    @Expose @SerializedName("links") Links links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(UserProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}

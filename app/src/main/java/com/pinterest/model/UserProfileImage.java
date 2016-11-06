package com.pinterest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by harsh on 05/11/16.
 */
public class UserProfileImage implements Serializable {
    @Expose
    @SerializedName("small") String small;
    @Expose @SerializedName("medium") String medium;
    @Expose @SerializedName("large") String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}

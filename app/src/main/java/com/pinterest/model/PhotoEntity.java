package com.pinterest.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by harsh on 05/11/16.
 */
public class PhotoEntity implements Serializable{
    ArrayList<PhotoCategories> photoEntityList;

    public ArrayList<PhotoCategories> getPhotoEntityList() {
        return photoEntityList;
    }

    public void setPhotoEntityList(ArrayList<PhotoCategories> photoEntityList) {
        this.photoEntityList = photoEntityList;
    }
}

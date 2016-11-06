package com.pinterest.home;

import com.pinterest.model.User;

/**
 * Created by harsh on 06/11/16.
 */
public interface CategoryListener {
    void onCategoryClicked();
    void onUserClicked(User user);
}

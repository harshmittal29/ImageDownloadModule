package com.pinterest.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.imageloading.ImageLoader;
import com.pinterest.R;
import com.pinterest.common.Constants;
import com.pinterest.common.Utils;
import com.pinterest.model.User;

/**
 * Created by harsh on 06/11/16.
 */
public class UserActivity extends AppCompatActivity {

    private ViewHolder mHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mHolder = new ViewHolder();
        setupActionBar();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null && bundle.containsKey(Constants.USER)){
            User user = (User) bundle.getSerializable(Constants.USER);
            setUpUserDetails(user);
        }
    }
    /**
     * setting up user details
     */
    private void setUpUserDetails(User user){
        if(user!=null){
            if(user.getProfileImage()!=null){
                if(!Utils.isNullOrEmpty(user.getProfileImage().getSmall())){
                    ImageLoader.displayImage(mHolder.userProfileImage, null, user.getProfileImage().getSmall(), ImageLoader.OPTION_NO_IMAGE_BACKGROUND_WHILE_LOADING);
                }
            }
            mHolder.userName.setText(user.getUserName());
            mHolder.title.setText(user.getUserName());
        }
    }

    /**
     * setting up action bar
     */
    private void setupActionBar() {
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        } catch (NoClassDefFoundError e) {
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * view holder pattern
     *
     */
    class ViewHolder{
        ImageView userProfileImage;
        TextView userName;
        TextView title;
        ViewHolder() {
            userProfileImage = (ImageView) findViewById(R.id.user_profile_image);
            userName = (TextView) findViewById(R.id.user_name);
            title = (TextView) findViewById(R.id.title);
        }
    }
}

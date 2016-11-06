package com.pinterest.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pinterest.R;
import com.pinterest.common.Constants;
import com.pinterest.common.Utils;
import com.pinterest.model.User;
import com.pinterest.user.UserActivity;

/**
 * Created by harsh on 05/11/16.
 */
public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, CategoryListener {

    private ViewHolder mHolder;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mHolder = new ViewHolder();
        mAdapter = new HomeAdapter(this, null);
        wireUpWidgets();
        setupActionBar();
        getDataAsync(false);
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
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowHomeEnabled(false);
                actionBar.setHomeButtonEnabled(false);
                ((TextView)findViewById(R.id.title)).setText(getResources().getString(R.string.categories));
            }
        } catch (NoClassDefFoundError e) {
        }
    }

    /**
     * setting up click listeners
     */
    private void wireUpWidgets() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mHolder.homeRecyclerView.setLayoutManager(linearLayoutManager);
        mHolder.homeRecyclerView.setAdapter(mAdapter);
        mHolder.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAsync(false);
            }
        });
    }

    /**
     * async to fetch data
     * @param swipe true or false
     */
    private void getDataAsync(final boolean swipe){
        new GetDataAsync() {
            @Override
            void onStart() {
                if(swipe){
                    mHolder.homeSwipeRefresh.setRefreshing(true);
                }else{
                    mHolder.progressBar.setVisibility(View.VISIBLE);
                    mHolder.noContentView.setVisibility(View.GONE);
                }
            }

            @Override
            void onFinish() {
                if(mHolder.homeSwipeRefresh.isRefreshing())
                    mHolder.homeSwipeRefresh.setRefreshing(false);
                mHolder.progressBar.setVisibility(View.GONE);
                mAdapter.setResponse(response);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            void onError() {
                if(mHolder.homeSwipeRefresh.isRefreshing())
                    mHolder.homeSwipeRefresh.setRefreshing(false);
                mHolder.progressBar.setVisibility(View.GONE);
                mHolder.noContentView.setVisibility(View.VISIBLE);
                if(Utils.isNetworkAvailable(HomeActivity.this)){
                    mHolder.noContentViewText.setText(getResources().getString(R.string.server_error));
                }else{
                    mHolder.noContentViewText.setText(getResources().getString(R.string.check_internet_connection));
                }
            }
        }.fetchData(this);
    }

    @Override
    public void onRefresh() {
        getDataAsync(true);
    }

    @Override
    public void onCategoryClicked() {

    }

    @Override
    public void onUserClicked(User user) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(Constants.USER, user);
        startActivity(intent);
    }

    /**
     * view holder pattern
     *
     */
    class ViewHolder{

        RecyclerView homeRecyclerView;
        SwipeRefreshLayout homeSwipeRefresh;
        RelativeLayout progressBar;
        RelativeLayout noContentView;
        TextView noContentViewText;
        Button retryButton;

        ViewHolder() {
            progressBar = (RelativeLayout) findViewById(R.id.progress_bar_layout);
            homeRecyclerView = (RecyclerView) findViewById(R.id.home_recycler_view);
            homeSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.home_swipe_refresh);
            noContentView = (RelativeLayout) findViewById(R.id.noContentView);
            retryButton = (Button) findViewById(R.id.retryButton);
            noContentViewText = (TextView) findViewById(R.id.noContentViewText);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

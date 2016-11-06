package com.pinterest.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.imageloading.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.pinterest.R;
import com.pinterest.common.Utils;
import com.pinterest.model.PhotoCategories;

/**
 * Created by harsh on 05/11/16.
 */
public class CategoryAdapter extends RecyclerView.ViewHolder {

    private View category1;
    private View category2;
    private Context context;
    private CategoryListener mListener;

    public CategoryAdapter(Context context, View itemView, CategoryListener listener) {
        super(itemView);
        this.context = context;
        mListener = listener;
        category1 = itemView.findViewById(R.id.category_1);
        category2 = itemView.findViewById(R.id.category_2);

        int paddingBetween = context.getResources().getDimensionPixelOffset(R.dimen.padding_10) / 3;
        category1.setPadding(0, 0, paddingBetween, 0);
        category2.setPadding(paddingBetween, 0, paddingBetween, 0);

        int height = getCategoryImageHeight(context);
        category1.findViewById(R.id.image_container).getLayoutParams().height = height;
        category2.findViewById(R.id.image_container).getLayoutParams().height = height;
    }

    /**
     * bind data to view
     * @param tag contains a row data
     */
    public void bind(HomeAdapter.TagRow tag){
        bindViewCol(0, tag);
        bindViewCol(1, tag);
    }

    private void bindViewCol(int position, HomeAdapter.TagRow tagRow) {

        final View col = getColViewFromPosition(position);
        if(position<tagRow.tagTiles.size()){
            col.setVisibility(View.VISIBLE);
            final PhotoCategories category = tagRow.tagTiles.get(position);

            if(!Utils.isNullOrEmpty(category.getId())) {
                col.findViewById(R.id.item_layout).setVisibility(View.VISIBLE);
                ((TextView) col.findViewById(R.id.user_name)).setText(category.getUser().getName());
                String number = category.getLikes() == 1 ?  context.getResources().getString(R.string.x_like, category.getLikes()) : context.getResources().getString(R.string.x_likes, category.getLikes());
                ((TextView) col.findViewById(R.id.no_of_likes)).setText(number);
                if(category.isLikedByUser()){
                    ((TextView) col.findViewById(R.id.no_of_likes)).setTextColor(Color.RED);
                }else{
                    ((TextView) col.findViewById(R.id.no_of_likes)).setTextColor(Color.BLACK);
                }

                String userImage = "";
                if(category.getUser()!=null && category.getUser().getProfileImage()!=null){
                    userImage = category.getUser().getProfileImage().getSmall();
                }
                if(!Utils.isNullOrEmpty(userImage)) {
                    ImageLoader.displayImage((ImageView) col.findViewById(R.id.user_snippet_image), null, userImage, ImageLoader.OPTION_NO_IMAGE_BACKGROUND_WHILE_LOADING);
                }

                final boolean isImageInCache = ImageLoader.isImageAvailableInCache(category.getUrl().getThumb());
                ImageLoader.displayImage((ImageView) col.findViewById(R.id.p_image)
                        , null, category.getUrl().getThumb(),
                        isImageInCache ? ImageLoader.OPTION_DEFAULT : ImageLoader.OPTION_FORCE_FADE_NO_BACKGROUND,
                        new ImageLoader.ZImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(View view) {
                            }

                            @Override
                            public void onLoadingFailed(View view, FailReason failReason) {
                                try {
                                    ImageLoader.displayImage((ImageView) col.findViewById(R.id.p_image), null, null);
                                    col.findViewById(R.id.gradient).setVisibility(View.GONE);
                                    ((ImageView) col.findViewById(R.id.p_image)).setScaleType(ImageView.ScaleType.FIT_XY);
                                } catch (Throwable t) {
                                    t.printStackTrace();
                                }
                            }

                            @Override
                            public void onLoadingComplete(View view, Bitmap bitmap) {
                                if (!isImageInCache) {
                                    col.findViewById(R.id.gradient).setAlpha(0.0f);
                                    col.findViewById(R.id.gradient).setVisibility(View.VISIBLE);
                                    col.findViewById(R.id.gradient).animate()
                                            .alpha(0.6f)
                                            .setInterpolator(new FastOutSlowInInterpolator())
                                            .setDuration(1000);
                                } else {
                                    col.findViewById(R.id.gradient).setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onLoadingCancelled(View view) {
                            }
                        });
            }else{
            }
            col.findViewById(R.id.user_info_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onUserClicked(category.getUser());
                }
            });
            col.findViewById(R.id.image_container).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else{
            col.setVisibility(View.GONE);
        }
    }

    /**
     * calculate image height
     */
    public static int getCategoryImageHeight(Context mContext) {
        return Utils.getScreenWidth(mContext) / 3
                - 2 * mContext.getResources().getDimensionPixelOffset(R.dimen.padding_20) / 3
                - 2 * mContext.getResources().getDimensionPixelOffset(R.dimen.padding_20) / 3;
    }

    private View getColViewFromPosition(int i) {
        switch (i) {
            case 0:
                return category1;
            case 1:
                return category2;
        }
        return null;
    }
}

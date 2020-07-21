package com.sample.ui;

public class Item {
    private String mImageUrl;
    private String mCreatorImageUrl;
    private String mCreator;
    private int mLikes;

    public Item(String imageUrl, String creator, int likes, String creatorImageUrl) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mLikes = likes;
        mCreatorImageUrl = creatorImageUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
    public String getCreatorImageUrl() {
        return mCreatorImageUrl;
    }
    public String getCreator() {
        return mCreator;
    }

    public int getLikeCount() {
        return mLikes;
    }
}

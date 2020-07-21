package com.sample.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> implements Filterable {
    private Context mContext;
    private ArrayList<Item> mExampleList;
    private ArrayList<Item> filterList;

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";
    public static final String EXTRA_CREATOR_IMAGE = "creatorImage";

    public Adapter(Context context, ArrayList<Item> exampleList) {
        mContext = context;
        mExampleList = exampleList;
        filterList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Item currentItem = mExampleList.get(position);

        final String imageUrl = currentItem.getImageUrl();
        final String creatorName = currentItem.getCreator();
        final int likeCount = currentItem.getLikeCount();
        final String creatorImageUrl = currentItem.getCreatorImageUrl();

        Picasso.with(mContext).load(imageUrl).fit().into(holder.mImageView);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(EXTRA_URL, imageUrl);
                intent.putExtra(EXTRA_CREATOR, creatorName);
                intent.putExtra(EXTRA_LIKES, likeCount);
                intent.putExtra(EXTRA_CREATOR_IMAGE, creatorImageUrl);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public CardView mCardView;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_grid);
            mCardView = itemView.findViewById(R.id.cardView_grid);
        }
    }
}


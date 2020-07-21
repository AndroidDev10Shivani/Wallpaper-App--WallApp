package com.sample.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {
    private Context mContext;
    private ArrayList<String> mImageText;
    private ArrayList<String> mImage;

    public CollectionAdapter(Context context, ArrayList<String> imageText, ArrayList<String> image) {
        mContext = context;
        mImageText = imageText;
        mImage = image;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_listview, parent, false);
        return new CollectionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, final int position) {
        Picasso.with(mContext).load(mImage.get(position)).into(holder.mImageView);
        holder.mTextView.setText(mImageText.get(position));

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CollectionGridActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("keyword", mImageText.get(position));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageText.size();
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
        public CardView mCardView;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_id);
            mTextView = itemView.findViewById(R.id.text_id);
            mCardView = itemView.findViewById(R.id.cardView_list);
        }
    }
}


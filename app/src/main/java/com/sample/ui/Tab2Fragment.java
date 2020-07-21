package com.sample.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Tab2Fragment extends Fragment {
    private ArrayList<String> imageText, images;
    private RecyclerView mRecyclerView;
    private CollectionAdapter mAdapter;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab2, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_tan2);

        imageText = new ArrayList<>();
        imageText.add("Nature");
        imageText.add("Food");
        imageText.add("Flower");
        imageText.add("Retro");
        imageText.add("Fruit");
        imageText.add("Animal");
        imageText.add("Vehicle");

        images = new ArrayList<>();
        images.add("https://cdn.pixabay.com/photo/2015/03/26/09/45/maple-leaves-690233_960_720.jpg");//nature
        images.add("https://cdn.pixabay.com/photo/2016/11/21/15/52/appetizer-1846083_960_720.jpg");//food
        images.add("https://cdn.pixabay.com/photo/2013/04/03/21/25/cereals-100263_960_720.jpg");//flower
        images.add("https://cdn.pixabay.com/photo/2016/09/01/19/53/pocket-watch-1637396_960_720.jpg");//retro
        images.add("https://cdn.pixabay.com/photo/2016/08/07/15/02/blueberries-1576409_960_720.jpg");//fruit
        images.add("https://cdn.pixabay.com/photo/2014/10/01/10/44/hedgehog-468228_960_720.jpg");//animal
        images.add("https://cdn.pixabay.com/photo/2016/11/19/12/15/bicycle-1838972_960_720.jpg");//vehical

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemCall();
        return view;
    }

    private void itemCall() {
        mAdapter = new CollectionAdapter(getContext(), imageText, images);
        mRecyclerView.setAdapter(mAdapter);
    }
}


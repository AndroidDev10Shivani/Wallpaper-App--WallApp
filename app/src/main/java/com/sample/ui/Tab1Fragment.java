package com.sample.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tab1Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<Item> mExampleList;
    private RequestQueue mRequestQueue;
    TextView textView_search;
    String url, searchText, filterType, KEY;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab1, container, false);

       /* textView_search = view.findViewById(R.id.search_text);
        view.findViewById(R.id.search_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText = textView_search.getText().toString();
                System.out.println("searchText :"+searchText);
                url = "https://pixabay.com/api/?key="+KEY+"&q="+searchText+"&image_type=illustration,photo&editors_choice=true&order=latest&pretty=true";
            }
        });

        view.findViewById(R.id.filter_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.menu_filter_list);
                dialog.findViewById(R.id.latest).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterType = "Latest";
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.popular).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.illustration).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.photo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });*/

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON();
        return view;
    }

    private void parseJSON() {
        System.out.println("parseJSON :");
       // url = "https://pixabay.com/api/?key="+KEY+"&image_type=illustration,photo&editors_choice=true&order=latest&pretty=true";
        url = "https://pixabay.com/api/?key="+KEY+"&q=nature&image_type=illustration,photo&editors_choice=true&order=latest&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String creatorName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int likeCount = hit.getInt("likes");
                                String creatorImageUrl = hit.getString("userImageURL");

                                mExampleList.add(new Item(imageUrl, creatorName, likeCount, creatorImageUrl));
                            }

                            mAdapter = new Adapter(getContext(), mExampleList);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}

package com.example.suno.boostcamp2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suno.boostcamp2.data.FamousPlace;
import com.example.suno.boostcamp2.R;
import com.example.suno.boostcamp2.RecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by suno on 2017. 7. 13..
 */

public class FamousPlaceFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<FamousPlace> itemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_famous_place, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = view.getContext();
        itemList = new ArrayList<>();

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RecyclerAdapter(itemList, getActivity()));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

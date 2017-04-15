package com.example.custom.customui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29185 on 2017/3/18.
 */

public class FragConMajor extends Fragment {
    @Nullable
    private List<Rank> rankList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank,container,false);
        initRanks();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RankAdapter adapter = new RankAdapter(rankList);
        recyclerView.setAdapter(adapter);
      /*  mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext()*//*this*//*);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RankAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);*/

        return view;
    }
    private void initRanks(){
        rankList.clear();
        Rank one = new Rank("1","李晓达","计算机科学与技术",R.drawable.m_one);
        rankList.add(one);
        Rank two = new Rank("2","王宝中","计算机科学与技术",R.drawable.m_two);
        rankList.add(two);
        Rank three = new Rank("3","翟瑞","计算机科学与技术",R.drawable.m_three);
        rankList.add(three);
        Rank four = new Rank("4","章知熊","计算机科学与技术",R.drawable.m_four);
        rankList.add(four);
        Rank five = new Rank("5","贾谊微","计算机科学与技术",R.drawable.m_five);
        rankList.add(five);
        Rank six = new Rank("6","张朔","计算机科学与技术",R.drawable.m_six);
        rankList.add(six);
        Rank seven = new Rank("7","李光","计算机科学与技术",R.drawable.m_seven);
        rankList.add(seven);
        Rank eight = new Rank("8","杨廣","计算机科学与技术",R.drawable.m_eight);
        rankList.add(eight);
        Rank nine = new Rank("9","牟硕","计算机科学与技术",R.drawable.m_nine);
        rankList.add(nine);
    }
    }


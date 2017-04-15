package com.example.custom.customui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29185 on 2017/3/13.
 */

public class FragmentRank extends android.support.v4.app.Fragment {

    private List<Rank>rankList = new ArrayList<>();
    /*private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;*/
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
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
        Rank one = new Rank("1","孟恬","会计系",R.drawable.bear);
        rankList.add(one);
        Rank two = new Rank("2","章小兰","土木工程系",R.drawable.two);
        rankList.add(two);
        Rank three = new Rank("3","张晓红","美术系",R.drawable.three);
        rankList.add(three);
        Rank four = new Rank("4","万恬强","体育系",R.drawable.four);
        rankList.add(four);
        Rank five = new Rank("5","高亮","体育系",R.drawable.five);
        rankList.add(five);
        Rank six = new Rank("6","刘天爱","文学系",R.drawable.six);
        rankList.add(six);
        Rank seven = new Rank("7","李小明","计算机科学与技术",R.drawable.d_one);
        rankList.add(seven);
        Rank eight = new Rank("8","王睿","信息管理",R.drawable.d_two);
        rankList.add(eight);
        Rank nine = new Rank("9","杨红","物联网",R.drawable.d_three);
        rankList.add(nine);
    }
}


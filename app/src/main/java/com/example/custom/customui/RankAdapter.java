package com.example.custom.customui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 29185 on 2017/3/16.
 */
public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private List<Rank> mRankList;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView rankHead;
        public TextView rankId;
        public TextView rankMajor;
        public TextView rankName;
        public ViewHolder (View view){
            super(view);
            rankHead = (ImageView)view.findViewById(R.id.rank_head);
            rankId = (TextView)view.findViewById(R.id.rank_id);
            rankMajor = (TextView)view.findViewById(R.id.rank_major);
            rankName = (TextView)view.findViewById(R.id.rank_name);
        }
    }

    public RankAdapter(List<Rank>rankList){
        mRankList = rankList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_layout,parent,false);//具体意思并没弄清楚
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rank rank = mRankList.get(position);
        holder.rankHead.setImageResource(rank.getImageId());
        holder.rankId.setText(rank.getId());
        holder.rankMajor.setText(rank.getMajor());
        holder.rankName.setText(rank.getName());
    }

    @Override
    public int getItemCount() {
        return mRankList.size();
    }
}

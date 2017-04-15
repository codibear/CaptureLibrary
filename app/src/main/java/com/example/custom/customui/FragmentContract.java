package com.example.custom.customui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 29185 on 2017/3/13.
 */

public class FragmentContract extends android.support.v4.app.Fragment {

    private FragmentTabHost mTabHost;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.fragmentcontract);

        Bundle arg1 = new Bundle();
        arg1.putInt("arg for fragment1",1);
        mTabHost.addTab(mTabHost.newTabSpec("Tab1").setIndicator("本系"),FragConDepartment.class,null);

        Bundle arg2 = new Bundle();
        arg2.putInt("arg2 for fragment2",2);
        mTabHost.addTab(mTabHost.newTabSpec("Tab2").setIndicator("本专业"),FragConMajor.class,arg2);

        /*mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if()
            }
        });*/
        return mTabHost;

/*
        View view = inflater.inflate(R.layout.fragment_contract,container,false);

        return mTabHost;*/
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}

package com.example.custom.customui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentContract fragmentContract;
    private FragmentRank fragmentRank;
    private FragmentTimer fragmentTimer;

    private ImageView imageTimer,imageRank,imageConstract;
    private DrawerLayout mDrawerLayout;
    private LinearLayout linearContract;
    private LinearLayout linearTimer;
    private LinearLayout linearRank;
    private FragmentManager fragmentManager;
    private FrameLayout contanier;//装载你的fragment的容器，add，replace的就是这里面放的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();

        //start  在activity显示的时候最好是又一个默认的显示页面，一般都是默认显示第一个，所以是add方法，到后面之后就额可以replace方法

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentTimer = new FragmentTimer();
        transaction.add(R.id.contanier, fragmentTimer);
        transaction.commit();
        //end


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
                break;
        }
        return true;
    }


    public void initView() {

        linearContract = (LinearLayout) findViewById(R.id.contractLayout);
        linearTimer = (LinearLayout) findViewById(R.id.timerLayout);
        linearRank = (LinearLayout) findViewById(R.id.rankLayout);

        imageRank = (ImageView)findViewById(R.id.image_rank);
        imageTimer = (ImageView)findViewById(R.id.image_timer);
        imageConstract = (ImageView)findViewById(R.id.image_contact);
        imageTimer.setImageResource(R.drawable.ic_timer_on);

        contanier = (FrameLayout) findViewById(R.id.contanier);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navView.setCheckedItem(R.id.nav_decro);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        linearContract.setOnClickListener(this);
        linearTimer.setOnClickListener(this);
        linearRank.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contractLayout://点击方法应该是你想点击的那个控件的id，而且可以直接在这里面进行操作了，你又写了下面的selectTab（）有点多余，不过看着清晰
                selectTab(0);
                imageConstract.setImageResource(R.drawable.ic_constract_on);
                break;
            case R.id.timerLayout:
                selectTab(1);
                imageTimer.setImageResource(R.drawable.ic_timer_on);
                break;
            case R.id.rankLayout:
                selectTab(2);
                imageRank.setImageResource(R.drawable.ic_rank_on);
                break;
            default:
                break;

        }
    }

    private void initImage(){
        imageRank.setImageResource(R.drawable.ic_rank_off);
        imageConstract.setImageResource(R.drawable.ic_constract_off);
        imageTimer.setImageResource(R.drawable.ic_timer_off);
    }
    private void selectTab(int i) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        initImage();
        switch (i) {
            case 0:
                if(fragmentContract == null){
                fragmentContract = new FragmentContract();
                transaction.add(R.id.contanier, fragmentContract);
                }else{
                     transaction.show(fragmentContract);
                }

                break;

            case 1:
                if(fragmentTimer == null){
               fragmentTimer = new FragmentTimer();
                transaction.add(R.id.contanier, fragmentTimer);
                }else{
                    transaction.show(fragmentTimer);
                }

                break;

            case 2:
                if(fragmentRank == null) {
                    fragmentRank = new FragmentRank();
                    transaction.add(R.id.contanier, fragmentRank);
                }else{
                    transaction.show(fragmentRank);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(fragmentContract != null){
            fragmentTransaction.hide(fragmentContract);
        }
        if(fragmentRank != null){
            fragmentTransaction.hide(fragmentRank);
        }
        if(fragmentTimer != null){
            fragmentTransaction.hide(fragmentTimer);
        }
    }

}


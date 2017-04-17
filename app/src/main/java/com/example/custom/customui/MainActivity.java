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
    private FrameLayout contanier;   //装载fragment的容器，add，replace的就是这里面放的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();

        //默认当前的Activity显示一个Fragment，否则的话就会出现白板

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentTimer = new FragmentTimer();
        transaction.add(R.id.contanier, fragmentTimer);
        transaction.commit();
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

    //初始化界面
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
    //通过选取的Layout（注意这里并不是图标），来切换界面，选中的同时将图标换成选中的状态
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contractLayout:
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

    /**
     * 判断Fragment是否存在，存在的话，隐藏特们以免出现Fragment重叠现象
     * @param fragmentTransaction
     */
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


package com.example.custom.customui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 29185 on 2017/3/13.
 * 计时用的Fragment
 */

public class FragmentTimer extends android.support.v4.app.Fragment  {

    static long start_time = 0 ;    //用于记录开始时间
    static long end_time = 0 ;      //用于记录终止时间
    static long mRecordTime = 0;    //用于记录开始后所得到的时间，再次计时使用

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_timer,container,false);
        final ImageButton startButton = (ImageButton)view.findViewById(R.id.start);
        final ImageButton stopButton = (ImageButton)view.findViewById(R.id.stop);
        final Chronometer chronometer = (Chronometer)view.findViewById(R.id.timer);
        final Button submit = (Button)view.findViewById(R.id.submit);
        final TextView textTime = (TextView)view.findViewById(R.id.learntime) ;

        submit.setEnabled(false);       //默认提交时间的按钮是不能点击的，只有当终止后才能点击
        stopButton.setEnabled(false);   //默认终止时间的按钮是不能点击的，只有当开始后才能点击
        startButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
/**
 * 如果有记录的时间，所得到的时间应该是（当前时间-记录时间）
 */
            if (mRecordTime != 0) {
                chronometer.setBase(chronometer.getBase() + (SystemClock.elapsedRealtime() - mRecordTime));
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime());
            }

            start_time = SystemClock.elapsedRealtime();
            chronometer.start();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            submit.setEnabled(false);
            textTime.setText("正在计时");

        }
    });
        stopButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            chronometer.stop();
            mRecordTime = SystemClock.elapsedRealtime();
            end_time = chronometer.getBase();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            submit.setEnabled(true);
            textTime.setText("计时停止");
        }
    });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               long time1 = SystemClock.elapsedRealtime() - start_time;
                long time2 = (time1/1000)/59;       //获得分钟
               long  time = (time1/1000)%59;        //获得秒
                textTime.setText("您学习了"+time2+"分"+time+"秒");
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                Toast.makeText(getActivity(),"您的数据已提交",Toast.LENGTH_SHORT).show();
                submit.setEnabled(false);
            }
        });
        return view;
    }

}


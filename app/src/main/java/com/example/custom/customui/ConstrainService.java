package com.example.custom.customui;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ConstrainService extends Service {
    private ActivityManager activityManager;

    private String lastTaskName;//1

    private Timer timer;
    private TimerTask task = new TimerTask(){

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if(activityManager == null){
                activityManager = (ActivityManager) ConstrainService.this.getSystemService(ACTIVITY_SERVICE);
            }

            List<ActivityManager.RecentTaskInfo> recentTasks = activityManager.getRecentTasks(2, ActivityManager.RECENT_WITH_EXCLUDED);

            ActivityManager.RecentTaskInfo recentInfo = recentTasks.get(0);
            Intent intent = recentInfo.baseIntent;
            String recentTaskName = intent.getComponent().getPackageName();

            if(lastTaskName != null){
                if(!lastTaskName.equals(recentTaskName) && !recentTaskName.equals("com.example.custom.customui") && !recentTaskName.equals("com.android.launcher")){
                    Log.d("recentPackageName", recentTaskName);
                    Intent intentNewActivity = new Intent(ConstrainService.this, MainActivity.class);
                    intentNewActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intentNewActivity);
                    //Toast.makeText(XuebaService.this, lastTaskName, Toast.LENGTH_SHORT).show();
                }
            }

            lastTaskName = recentTaskName;

        }

    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        timer = new Timer();
        timer.schedule(task, 0, 20);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}

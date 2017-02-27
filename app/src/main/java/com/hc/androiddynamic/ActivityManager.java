package com.hc.androiddynamic;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/12/28.
 */
public class ActivityManager {
    private static List<Activity> activityList = new ArrayList<>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    /**
     * 获取栈顶Activity
     * @return
     */
    public static Activity getTopActivity(){
        if (activityList.isEmpty()){
            return  null;
        }else {
            return activityList.get(activityList.size()-1);
        }
    }
}

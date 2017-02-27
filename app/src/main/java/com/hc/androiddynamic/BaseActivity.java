package com.hc.androiddynamic;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2016/12/28.
 */
public class BaseActivity extends AppCompatActivity {
    private static PermissionListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    public  static  void requestRuntimePermission(String[] permissions, PermissionListener listener){
        Activity topActivity = ActivityManager.getTopActivity();
        if (topActivity == null){
            return;
        }
        mListener = listener;
        //多个权限处理
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission)!= PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission);
            }

        }
        if (!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(topActivity,permissionList.toArray(new String[permissionList.size()]),1);
        }else {
//            doSomething();
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> deniedPermissions = new ArrayList<>();
        switch (requestCode){
            case 1:

                if (grantResults.length > 0){
                    for(int i=0 ; i< grantResults.length ; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission);
                        }
                    }
                    //说明没有被拒绝的权限
                    if (deniedPermissions.isEmpty()){
                        mListener.onGranted();
                    }else {
                        mListener.onDenied(deniedPermissions);
                    }


                }

                break;
        }
    }
}

package com.hc.androiddynamic;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainTwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View view){
        requestRuntimePermission(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainTwoActivity.this,"所有权限被授权",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                for (String deniedPermission : deniedPermissions) {
                    Toast.makeText(MainTwoActivity.this,"被拒绝权限为:"+deniedPermission,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}

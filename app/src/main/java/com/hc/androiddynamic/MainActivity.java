package com.hc.androiddynamic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View view){
        //多个权限处理
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CALL_PHONE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_CONTACTS);

        }

        if (!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]),1);
        }else {
            doSomething();
        }


//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
//
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
//        }else {
//            //授权了
//            callPhone();
//        }

    }

    private void doSomething(){
        //已经授权
        //do something
        Toast.makeText(MainActivity.this,"所有申请的权限同意授权",Toast.LENGTH_SHORT).show();
    }

    private void callPhone(){
        try{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel://10086"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:

                if (grantResults.length > 0){
                    for(int grantResult:grantResults){
                        if (grantResult != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(MainActivity.this,"某个权限被拒绝了",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    doSomething();

                }
//                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    callPhone();
//                    //同意授权
//                }else {
//                    Toast.makeText(MainActivity.this,"没有同意授权",Toast.LENGTH_SHORT).show();
//                }
                break;
        }
    }
}

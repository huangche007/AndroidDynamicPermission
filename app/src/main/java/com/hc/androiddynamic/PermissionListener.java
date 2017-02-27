package com.hc.androiddynamic;

import java.util.List;

/**
 * Created by X on 2016/12/28.
 */
public interface PermissionListener {
    void onGranted();//授权成功的回调

    /**
     * 授权失败的回调
     * @param deniedPermissions:被拒绝的权限集合
     */
    void onDenied(List<String> deniedPermissions);
}

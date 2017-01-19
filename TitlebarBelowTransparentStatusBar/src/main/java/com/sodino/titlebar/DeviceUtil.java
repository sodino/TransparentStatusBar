package com.sodino.titlebar;

import android.os.Build;

/**
 * Created by Administrator on 2017/1/19.
 */

public class DeviceUtil {
    public static final int BASE = 1000;
    public static final int XIAOMI = BASE + BASE;
    public static final int MEIZU = XIAOMI + BASE;

    public static boolean isMIUI() {
        return Build.BRAND.toLowerCase().contains("xiaomi");
    }

    public static boolean isMeizu() {
        return Build.BRAND.toLowerCase().contains("meizu");
    }
}

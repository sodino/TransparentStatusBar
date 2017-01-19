package com.sodino.titlebar;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.view.Window;

//import com.eln.lib.thread.ThreadPool;
//import com.eln.lib.thread.ThreadPriority;
//import com.eln.lib.util.device.DeviceUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/1/18.
 */

public class StatusbarColorUtils {
    public static void fix(final int phone, final Activity baseActivity, final boolean dark) {
//        ThreadPool.post(new Runnable("fixStatuscolor", ThreadPriority.DATA_READ_WRITE) {
//            @Override
//            public void run() {
//                switch(phone) {
//                    case DeviceUtil.MEIZU:{
//                        fixMeizu(baseActivity, dark);
//                    }break;
//                    case DeviceUtil.XIAOMI:{
//                        fixXiaomi(baseActivity, dark);
//                    }break;
//                }
//            }
//        });

//        if ( do thread start == false) {
            HandlerThread hThread = new HandlerThread("fixStatuscolor", Process.THREAD_PRIORITY_BACKGROUND);
            hThread.start();
//        }
        new Handler(hThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                switch(phone) {
                    case DeviceUtil.MEIZU:{
                        fixMeizu(baseActivity, dark);
                    }break;
                    case DeviceUtil.XIAOMI:{
                        fixXiaomi(baseActivity, dark);
                    }break;
                }
            }
        });
    }

    private static void fixXiaomi(Activity baseActivity, boolean dark) {
        // http://dev.xiaomi.com/docs/appsmarket/technical_docs/immersion/
        Class<? extends Window> clazz = baseActivity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(baseActivity.getWindow(), dark ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fixMeizu(Activity baseActivity, boolean dark) {
        MeizuStatusbarColorUtils.setStatusBarDarkIcon(baseActivity, dark);
    }

    public static boolean isFixWhiteTextColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // 有亮色模式，也就不需要处理白色的文字冲突了
            return false;
        } else if (DeviceUtil.isMIUI()) {
            // MiUI自家的方法，也就不需要处理白色的文字冲突了
            return false;
        }else if (DeviceUtil.isMeizu()) {
            // Meizu自家的方法，也就不需要处理白色的文字冲突了
            return false;
        }
        return true;
    }
}

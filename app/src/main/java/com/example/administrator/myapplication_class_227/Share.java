package com.example.administrator.myapplication_class_227;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Share {
    private static String fileName="tag";//共享参数文件名字
    public static void saveTag( Context context){
        SharedPreferences sp=context.getSharedPreferences(fileName, Activity.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("tag",true);//存入标签值true
        editor.commit();
    }
    public static boolean getTag(Context context){
        SharedPreferences sp=context.getSharedPreferences(fileName, Activity.MODE_WORLD_READABLE);
        return sp.getBoolean("tag",false);//没取到默认值是false
    }

}

package com.example.administrator.myapplication_class_227.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.myapplication_class_227.R;

/**
 * Created by Administrator on 2017/2/27.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        afterInit();
        addLayout();
        initView();
    }
    //加载布局的方法
    public abstract  void addLayout();
    //findviewbyid控件初始化方法
    public abstract void initView();
    public abstract void afterInit();
    //吐司方法
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void showToast(int msg){
        Toast.makeText(this,msg+"",Toast.LENGTH_SHORT).show();
    }
    public void showToast(double msg){
        Toast.makeText(this,msg+"",Toast.LENGTH_SHORT).show();
    }

    public void gotoActivity(Class<?> toactivity,boolean close){
        Intent intent=new Intent(this,toactivity);
        startActivity(intent);
        overridePendingTransition(R.anim.jion,R.anim.chu);
//        if(close){
//            finish();
//        }
    }
}

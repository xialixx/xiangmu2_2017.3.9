package com.example.administrator.myapplication_class_227.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.Share;

/**
 * Created by Administrator on 2017/2/27.
 */
public class LogoActivity extends BaseActivity {
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    @Override
    public void addLayout() {
        if(!Share.getTag(this)){//默认是false
            Log.e("a",!Share.getTag(this)+"");
            gotoActivity(LeanActivity.class,true);
            finish();
        }
        setContentView(R.layout.layout_logo);
        if(Share.getTag(this)){
            jump();
        }
    }
    @Override
    public void initView() {
        imageView= (ImageView) findViewById(R.id.logo_image);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

    }
    @Override
    public void afterInit() {
    }
    public void jump(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoActivity(MainActivity.class,true);
                finish();
            }
        },2100);
    }
}



package com.example.administrator.myapplication_class_227.activity;

/**
 * Created by Administrator on 2017/2/27.
 */
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.Share;
import com.example.administrator.myapplication_class_227.adapter.ViewPagerAdapter;

public class LeanActivity extends BaseActivity {
private ViewPager viewPager;
private int page;
private ImageView[] views;
@Override
public void addLayout() {
        setContentView(R.layout.layout_two);
        }
@Override
public void initView() {
        viewPager= (ViewPager) findViewById(R.id.two_viewpager);
        //数据源图片
        views=new ImageView[3];
        for(int i=0;i<views.length;i++){
        views[i]=new ImageView(this);
        }
        views[0].setBackgroundResource(R.drawable.timg);
        views[1].setBackgroundResource(R.drawable.timg);
        views[2].setBackgroundResource(R.drawable.timg);
        //设置适配器
        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
@Override
public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
@Override
public void onPageSelected(int position) {
        page=position;
        }
@Override
public void onPageScrollStateChanged(int state) {
        }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
        float startX;
        float endX;
@Override
public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
        case MotionEvent.ACTION_DOWN:
        startX=event.getX();
        break;
        case MotionEvent.ACTION_UP:
        endX=event.getX();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width=metric.widthPixels;
        if(page==views.length-1&&(startX-endX)>width/4){
        gotoActivity(LogoActivity.class,true);
        finish();
        }
        break;
        case MotionEvent.ACTION_MOVE:
        break;
        }
        return false;
        }
        });
        }
@Override
public void afterInit() {
        Share.saveTag(this);
        }
        }

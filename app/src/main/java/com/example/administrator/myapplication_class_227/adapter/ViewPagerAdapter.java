package com.example.administrator.myapplication_class_227.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/2/27.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ImageView[] views;//数据源传进来
    public ViewPagerAdapter(ImageView[] views){
        this.views=views;
    }

    @Override
    public int getCount() {
        return views.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    //生成你要滑动的这一页
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views[position]);
//        Log.e("msg","生成的position"+position);
        return views[position] ;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        Log.e("msg","销毁的position"+position);
        container.removeView(views[position]);
    }
}


package com.example.administrator.myapplication_class_227.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/28.
 */
public class MyAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private String [] name;
    public MyAdapter(FragmentManager fm, ArrayList<Fragment> list,String [] name) {
        super(fm);
        this.list=list;
        this.name=name;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}

package com.example.administrator.myapplication_class_227.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.myapplication_class_227.adapter.MyAdapter;
import com.example.administrator.myapplication_class_227.R;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Myfragment1 extends Fragment{
    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private TabLayout tabLayout;
    private String [] name={"英超","德甲","意甲","法甲","西甲","中超","NBA","收藏"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment1,null);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager1);
        tabLayout= (TabLayout) view.findViewById(R.id.frame1_tablayout);
        viewPager.setOffscreenPageLimit(3);
        myAdapter=new MyAdapter(getFragmentManager(),getData(),name);
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    public ArrayList<Fragment> getData(){
        Fragment fragmet;
        Bundle bundle;
        ArrayList<Fragment> list=new ArrayList<>();
         for(int i=0;i<name.length;i++){
             fragmet=new Myfragment1_son();
             bundle=new Bundle();
             bundle.putString("name",name[i]);
             fragmet.setArguments(bundle);
             list.add(fragmet);
         }
        return  list;
    }
}

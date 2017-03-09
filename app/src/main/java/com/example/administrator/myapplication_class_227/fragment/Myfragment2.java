package com.example.administrator.myapplication_class_227.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.adapter.MyAdapter;
import com.example.administrator.myapplication_class_227.user.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/27.
 */
public class Myfragment2 extends Fragment{
    private ViewPager viewPager;
    private MyAdapter myAdapter;
    private TabLayout tabLayout;
    private String [] name={"英超","德甲","意甲","法甲","西甲","中超"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment2,null);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager2);
        tabLayout= (TabLayout) view.findViewById(R.id.frame2_tablayout);
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
            fragmet=new Myfragmet2_son();
            bundle=new Bundle();
            bundle.putString("name",name[i]);
            fragmet.setArguments(bundle);
            list.add(fragmet);
        }
        return  list;
    }
}


package com.example.administrator.myapplication_class_227.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.activity.BaseActivity;
import com.example.administrator.myapplication_class_227.fragment.Myfragment1;
import com.example.administrator.myapplication_class_227.fragment.Myfragment2;
import com.example.administrator.myapplication_class_227.fragment.Myfragment3;
import com.example.administrator.myapplication_class_227.fragment.Myfragment4;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager;//管理者
    private FragmentTransaction transaction;//开启事务
    private Fragment[] fragments=new Fragment[4];
    private int now_count=0;
    private ImageView imageView1,imageView11,imageView2,imageView22,imageView3,imageView33,imageView4,imageView44;
    @Override
    public void addLayout() {
        setContentView(R.layout.activity_main);
    }
    @Override
    public void initView() {
        imageView1= (ImageView) findViewById(R.id.main_imageview1);
        imageView11= (ImageView) findViewById(R.id.main_imageview11);
        imageView2= (ImageView) findViewById(R.id.main_imageview2);
        imageView22= (ImageView) findViewById(R.id.main_imageview22);
        imageView3= (ImageView) findViewById(R.id.main_imageview3);
        imageView33= (ImageView) findViewById(R.id.main_imageview33);
        imageView4= (ImageView) findViewById(R.id.main_imageview4);
        imageView44= (ImageView) findViewById(R.id.main_imageview44);
        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        fragments[0]=new Myfragment1();
        transaction.add(R.id.frame_main,fragments[0]);//添加
        transaction.commit();
        now_count=0;
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.GONE);
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get(0);
                imageView1.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
            }
        });
        imageView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get(1);
                imageView2.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
            }
        });
        imageView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get(2);
                imageView3.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.GONE);
                imageView1.setVisibility(View.GONE);
                imageView4.setVisibility(View.GONE);
            }
        });
        imageView44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get(3);
                imageView4.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.GONE);
                imageView3.setVisibility(View.GONE);
                imageView1.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void afterInit() {
    }
    public void get(int num){
        if(now_count!=num){
            transaction=fragmentManager.beginTransaction();
            if(fragments[num]==null){
                fragments[num]=newFragment(num);
                transaction.add(R.id.frame_main,fragments[num]);
            }else{
                transaction.show(fragments[num]);
            }
            transaction.hide(fragments[now_count]);
            transaction.commit();
            now_count=num;
        }else{

        }
    }
    public Fragment newFragment(int index){
        switch (index){
            case 0:
                return  new Myfragment1();
            case 1:
                return  new Myfragment2();
            case 2:
                return  new Myfragment3();
            case 3:
                return  new Myfragment4();
        }
        return  null;
    }
}

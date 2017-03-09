package com.example.administrator.myapplication_class_227.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.activity.MainActivity;
import com.example.administrator.myapplication_class_227.activity.MyActivity3;
import com.example.administrator.myapplication_class_227.adapter.MyAdapter3;
import com.example.administrator.myapplication_class_227.adapter.MyAdapterNBA;
import com.example.administrator.myapplication_class_227.adapter.RecycleviewAdapter;
import com.example.administrator.myapplication_class_227.mvc.DB_C;
import com.example.administrator.myapplication_class_227.user.User;
import com.example.administrator.myapplication_class_227.user.User1;
import com.example.administrator.myapplication_class_227.user.UserNBA;
import com.example.administrator.myapplication_class_227.user.shoucangUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/28.
 */
public class Myfragment1_son extends Fragment{
    private String name;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private RecycleviewAdapter recycleviewAdapter;
    private MyAdapterNBA myAdapterNBA;
    private DB_C dbc;
    private ArrayList<shoucangUser> list2;
    private MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       name=getArguments().getString("name");
        View view=inflater.inflate(R.layout.layout_fragmet1_son,null);
         recyclerView= (RecyclerView) view.findViewById(R.id.fragment1son_recyclerview);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.frament1son_refreshlayout);
        dbc=new DB_C(getContext());
        list2=new ArrayList<>();


        recycleviewAdapter=new RecycleviewAdapter(getContext());
        recyclerView.setAdapter(recycleviewAdapter);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //点击跳转
        activity= (MainActivity) getActivity();
        //点击跳转
        recycleviewAdapter.setOnItemClickListener(new RecycleviewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position, String url, String time, String time1, String time2, String dui, String dui1) {
                    Intent intent=new Intent(activity, MyActivity3.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                    Toast.makeText(getContext(),"iii"+position+"   "+url,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnshouCangListerer(View view, int position, int image3, String url, String time, String time1, String time2, String dui, String dui1,String bifen) {
                if(image3==1){
                    Toast.makeText(getContext(),"收藏"+position,Toast.LENGTH_SHORT).show();

                    final int ischeak = image3;
                    final String C2 = time;
                    final String C3 = time1;
                    final String C1 = time2;
                    final String C4T1 = dui;
                    final String C4R = bifen;
                    final String C4T2 = dui1;
                    final String C52Link = url;
                    dbc.add(ischeak, C2, C3, C1,C4T1,C4R,C4T2,C52Link);

                    Log.e("集合","   "+dbc.showAll().toString());

                }else if(image3==2){
                    Toast.makeText(getContext(),"取消收藏"+position,Toast.LENGTH_SHORT).show();
                    dbc.deleteAll(dbc.showAll());
//                    recycleviewAdapter.update(dbc.showAll());
                    Log.e("集合1","   "+dbc.showAll().toString());
                }
            }
        });
        if(name.equals("NBA")){
            myAdapterNBA=new MyAdapterNBA(getContext());
            recyclerView.setAdapter(myAdapterNBA);
            tiao();
            getHttpnba(false);
        }else if(name.equals("收藏")){
//            recycleviewAdapter = new RecycleviewAdapter(dbc.showAll(),activity);
//            recycleviewAdapter.update(dbc.showAll());
        }else{
            getHttp(name,false);
        }
        fanFa();
        return view;
    }
    public void fanFa(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                if(name.equals("NBA")){
                    myAdapterNBA=new MyAdapterNBA(getContext());
                    recyclerView.setAdapter(myAdapterNBA);
                    tiao();
                    getHttpnba(true);
                }else if(name.equals("收藏")){
//                    recycleviewAdapter = new RecycleviewAdapter(dbc.showAll(),activity);
//                    recycleviewAdapter.update(dbc.showAll());
                }else{
                    getHttp(name,true);
                }
            }
        });

    }
    public void tiao(){
        //点击跳转
        final MainActivity activity= (MainActivity) getActivity();
        //点击跳转
        myAdapterNBA.setOnItemClickListener(new MyAdapterNBA.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position, String url) {
                Intent intent=new Intent(activity, MyActivity3.class);
                intent.putExtra("url",url);
                startActivity(intent);
                Toast.makeText(getContext(),"iii"+position+"   "+url,Toast.LENGTH_SHORT).show();
            }
        });
    }
    //足球接口
    public void getHttp(String name, final boolean isupdate){
            //1.初始化Retrofit
            //网址、解析工具（gosn等）
        //足球http://op.juhe.cn/onebox/football/combat?key=80a6944c90651664678b97cc30e88402&hteam=%E7%9A%87%E9%A9%AC&vteam=%E9%98%BF%E5%B0%94%E6%A2%85%E9%87%8C%E4%BA%9A
            //http://op.juhe.cn/onebox/football/league?key=80a6944c90651664678b97cc30e88402&league=%E8%A5%BF%E7%94%B2
            final Retrofit retrofit = new Retrofit.Builder().
                    baseUrl("http://op.juhe.cn/onebox/football/").
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
            //3.准备请求网络
        setWeatherService service=retrofit.create(setWeatherService.class);
            //4.通过回调获得结果
            Call<User> call=service.getBean("80a6944c90651664678b97cc30e88402",name);
            //5.请求加入调度，正式排队去网络获取数据
            call.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                   if(isupdate){
                      recycleviewAdapter.updateList(response.body().getResult().getViews().getSaicheng1());
                       refreshLayout.setRefreshing(false);
                   }else{
                       recycleviewAdapter.addList(response.body().getResult().getViews().getSaicheng1());
                   }
                    Log.e("aa","加载成功");
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("aa","加载失败");
                    refreshLayout.setRefreshing(false);
                }

            });
    }
    //定义接口
    public interface setWeatherService{
        @GET("league")
        Call<User> getBean(@Query("key") String key, @Query("league") String league);
    }

    //足球接口获取图片的接口
    public void getHttpjpg(String name, String name1){
        //1.初始化Retrofit
        //网址、解析工具（gosn等）
        //足球http://op.juhe.cn/onebox/football/combat?key=80a6944c90651664678b97cc30e88402&hteam=%E7%9A%87%E9%A9%AC&vteam=%E9%98%BF%E5%B0%94%E6%A2%85%E9%87%8C%E4%BA%9A
        //http://op.juhe.cn/onebox/football/league?key=80a6944c90651664678b97cc30e88402&league=%E8%A5%BF%E7%94%B2
        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://op.juhe.cn/onebox/football/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.准备请求网络
        getjpg service=retrofit.create(getjpg.class);
        //4.通过回调获得结果
        Call<User1> call=service.getBean("80a6944c90651664678b97cc30e88402",name,name1);
        //5.请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<User1>() {

            @Override
            public void onResponse(Call<User1> call, Response<User1> response) {
//                Log.e("123","  "+response.body().toString());
//                if(isupda){
//                    recycleviewAdapter.updateList(response.body().getResult().getList());
//                    refreshLayout.setRefreshing(false);
//                }else{
//                    recycleviewAdapter.addList1(response.body().getResult().getList());
//                }
                Log.e("aaa","加载成功");
            }
            @Override
            public void onFailure(Call<User1> call, Throwable t) {
                Log.e("aa","加载失败");
//                refreshLayout.setRefreshing(false);
            }

        });
    }
    //定义接口
    public interface getjpg{
        @GET("combat")
        Call<User1> getBean(@Query("key") String key, @Query("hteam") String hteam, @Query("vteam") String vteam);
    }

    //NBA接口
    public void getHttpnba(final boolean aa){
        //1.初始化Retrofit
        //网址、解析工具（gosn等）
        //篮球http://op.juhe.cn/onebox/basketball/nba?key=c3630b78e75fc7f2dd4c8481b092f6e2

        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://op.juhe.cn/onebox/basketball/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.准备请求网络
        setnba service=retrofit.create(setnba.class);
        //4.通过回调获得结果
        Call<UserNBA> call=service.getBean("c3630b78e75fc7f2dd4c8481b092f6e2");
        //5.请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<UserNBA>() {

            @Override
            public void onResponse(Call<UserNBA> call, Response<UserNBA> response) {
//                for(int i=0;i<10;i++){
//                    Log.e("aa","加载成功"+"   "+response.body().getResult().getList().get(2).getLive());
//                }
                if(aa){
                myAdapterNBA.updateList(response.body().getResult().getList());
                refreshLayout.setRefreshing(false);
            }else{
                    myAdapterNBA.addList(response.body().getResult().getList());
            }
            }
            @Override
            public void onFailure(Call<UserNBA> call, Throwable t) {
                Log.e("aa","加载失败");
                refreshLayout.setRefreshing(false);
            }
        });
    }
    //定义接口
    public interface setnba{
        @GET("nba")
        Call<UserNBA> getBean(@Query("key") String key);
    }
}

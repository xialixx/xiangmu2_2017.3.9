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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.activity.MainActivity;
import com.example.administrator.myapplication_class_227.activity.MyActivity3;
import com.example.administrator.myapplication_class_227.adapter.RecycleviewAdapter2;
import com.example.administrator.myapplication_class_227.adapter.RecycleviewAdapter2_2;
import com.example.administrator.myapplication_class_227.user.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/2.
 */
public class Myfragmet2_son extends Fragment {
    private String name;
    private SwipeRefreshLayout refreshLayout1,refreshLayout2;
    private RecyclerView recyclerView1,recyclerView2;
    private RecycleviewAdapter2 recycleviewAdapter;
    private RecycleviewAdapter2_2 recycleviewAdapter2_2;
    private Button b1,b2;
    private MainActivity activity;
    private LinearLayout layout1,layout2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        name=getArguments().getString("name");
        View view=inflater.inflate(R.layout.layout_fragment2_1son,null);
        recyclerView1= (RecyclerView) view.findViewById(R.id.fragment2son_recyclerview1);
        refreshLayout1= (SwipeRefreshLayout) view.findViewById(R.id.frament2son_refreshlayout1);
        recyclerView2= (RecyclerView) view.findViewById(R.id.fragment2son_recyclerview11);
        refreshLayout2= (SwipeRefreshLayout) view.findViewById(R.id.frament2son_refreshlayout11);
        b1= (Button) view.findViewById(R.id.fragment2_button1);
        b2= (Button) view.findViewById(R.id.fragment2_button2);
        layout1= (LinearLayout) view.findViewById(R.id.linearLayout1);
        layout2= (LinearLayout) view.findViewById(R.id.linearLayout2);

        jiFen();
        sheShou();
        buttonget();
        //点击跳转
         activity= (MainActivity) getActivity();
        //点击跳转

        return view;
    }
    public void jiFen(){
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleviewAdapter=new RecycleviewAdapter2(getContext());
        recyclerView1.setAdapter(recycleviewAdapter);
        recycleviewAdapter.setOnItemClickListener(new RecycleviewAdapter2.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position, String url) {
                Intent intent=new Intent(activity, MyActivity3.class);
                intent.putExtra("url",url);
                startActivity(intent);
                Toast.makeText(getContext(),"iii"+position+"   "+url,Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout1.setRefreshing(true);
                getHttp(name,true);
            }
        });
    }
    public void sheShou(){
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleviewAdapter2_2=new RecycleviewAdapter2_2(getContext());
        recyclerView2.setAdapter(recycleviewAdapter2_2);
        refreshLayout2.setColorSchemeResources(R.color.colorPrimary);
        recycleviewAdapter2_2.setOnItemClickListener(new RecycleviewAdapter2_2.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position, String url) {
                Intent intent=new Intent(activity, MyActivity3.class);
                intent.putExtra("url",url);
                startActivity(intent);
                Toast.makeText(getContext(),"iii"+position+"   "+url,Toast.LENGTH_SHORT).show();
            }
        });
        getHttp(name,false);
        refreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout2.setRefreshing(true);
                getHttp(name,true);
            }
        });
    }
    public void buttonget(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundColor(0xFF016BFF);
                b2.setBackgroundColor(0xfff16022);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundColor(0xFF016BFF);
                b1.setBackgroundColor(0xfff16022);
                layout2.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
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
                        recycleviewAdapter.updateList(response.body().getResult().getViews().getJifenbang());
                        recycleviewAdapter2_2.updateList(response.body().getResult().getViews().getSheshoubang());
                        refreshLayout1.setRefreshing(false);
                        refreshLayout2.setRefreshing(false);
                    }else{
                        recycleviewAdapter.addList(response.body().getResult().getViews().getJifenbang());
                        recycleviewAdapter2_2.addList(response.body().getResult().getViews().getSheshoubang());
                    }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("aa","加载失败");
                    refreshLayout1.setRefreshing(false);
                    refreshLayout2.setRefreshing(false);
            }
        });
    }
    //定义接口
    public interface setWeatherService{
        @GET("league")
        Call<User> getBean(@Query("key") String key, @Query("league") String league);
    }
}

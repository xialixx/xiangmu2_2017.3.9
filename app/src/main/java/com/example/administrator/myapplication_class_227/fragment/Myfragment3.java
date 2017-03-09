package com.example.administrator.myapplication_class_227.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.activity.MainActivity;
import com.example.administrator.myapplication_class_227.activity.MyActivity3;
import com.example.administrator.myapplication_class_227.adapter.MyAdapter3;
import com.example.administrator.myapplication_class_227.user.User3;

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
public class Myfragment3 extends Fragment{
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private MyAdapter3 myAdapter3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_myfragment3_son,null);
        recyclerView= (RecyclerView) view.findViewById(R.id.fragment3son_recyclerview);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.frament3son_refreshlayout);

        myAdapter3=new MyAdapter3(getContext());
        recyclerView.setAdapter(myAdapter3);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getHttp(false);
        //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);

                getHttp(true);
            }
        });
        final MainActivity activity= (MainActivity) getActivity();
        //点击跳转
        myAdapter3.setOnItemClickListener(new MyAdapter3.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position, String url) {
                Intent intent=new Intent(activity, MyActivity3.class);
                intent.putExtra("url",url);
                startActivity(intent);
                Toast.makeText(getContext(),"iii"+position+"   "+url,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    public void getHttp(final boolean isupat){
        //1.初始化Retrofit
        //网址、解析工具（gosn等）
        //http://v.juhe.cn/toutiao/index?type=tiyu&key=8b41621da64fb5a7db660117a62cfa36
        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://v.juhe.cn/toutiao/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.准备请求网络
        setWeatherService service=retrofit.create(setWeatherService.class);
        //4.通过回调获得结果
        Call<User3> call=service.getBean("tiyu","8b41621da64fb5a7db660117a62cfa36");
        //5.请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<User3>() {

            @Override
            public void onResponse(Call<User3> call, Response<User3> response) {
                Log.e("aa","加载成功"+response.body().getResult().getData().toString());
                if(isupat){
                    myAdapter3.updateList(response.body().getResult().getData());
                    refreshLayout.setRefreshing(false);
                }else{
                    myAdapter3.addList(response.body().getResult().getData());
                }
            }
            @Override
            public void onFailure(Call<User3> call, Throwable t) {
                Log.e("aa","加载失败");
                refreshLayout.setRefreshing(false);
            }
        });
    }
    //定义接口
    public interface setWeatherService{
        @GET("index")
        Call<User3> getBean(@Query("type") String type, @Query("key") String key);
    }
}

package com.example.administrator.myapplication_class_227.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.fragment.Myfragment1_son;
import com.example.administrator.myapplication_class_227.mvc.DB_C;
import com.example.administrator.myapplication_class_227.user.User.ResultBean.ViewsBean.Saicheng1Bean;
import com.example.administrator.myapplication_class_227.user.User1.ResultBean.ListBean;
import com.example.administrator.myapplication_class_227.user.shoucangUser;
import com.squareup.picasso.Picasso;

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
 * Created by Administrator on 2017/3/1.
 */
public class RecycleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Myfragment1_son myfragment1_son=new Myfragment1_son();
    private ArrayList<Saicheng1Bean> list;
    private ArrayList<ListBean> list1;
    private Context context;
    private ArrayList<shoucangUser> list2;
    private DB_C dbc;
    private int a=0;
    public RecycleviewAdapter(Context context){
        this.list=new ArrayList<>();
        this.list1=new ArrayList<>();
        this.context=context;
    }
    //添加数据
    public void addList(List<Saicheng1Bean> list){
        if(this.list.containsAll((ArrayList)list)){
            for(Saicheng1Bean u:list){
                myfragment1_son.getHttpjpg(u.getC4T1(),u.getC4T2());
            }
            return;
        }
        this.list.addAll((ArrayList)list);
        notifyDataSetChanged();
        for(Saicheng1Bean u:list){
            myfragment1_son.getHttpjpg(u.getC4T1(),u.getC4T2());
        }
    }
    //更新数据
    public void updateList(List<Saicheng1Bean> list){
        this.list=(ArrayList)list;
        notifyDataSetChanged();
    }
//    public RecycleviewAdapter(List<Saicheng1Bean> list, Context context){
//        this.list=(ArrayList)list;
//        this.context=context;
//    }
//
//    public void update(ArrayList<Saicheng1Bean> list){
//        this.list=list;
//        notifyDataSetChanged();
//    }
    //添加图片
//    public void addList1(List<ListBean> list1){
//        if(this.list1.containsAll((ArrayList)list1)){
//            return;
//        }
//        this.list1.addAll((ArrayList)list1);
//        notifyDataSetChanged();
//    }


    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder=null;
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fragment1_adapter_son,null);
            holder=new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder holder1= (MyViewHolder) holder;
        holder1.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    Toast.makeText(context,"点了"+position,Toast.LENGTH_SHORT).show();
                    listener.OnItemClickListener(v,position,list.get(position).getC52Link(), list.get(position).getC2(),list.get(position).getC3(),list.get(position).getC1(),list.get(position).getC4T1(),list.get(position).getC4T2());
                }
            }
        });
        holder1.t1.setText(list.get(position).getC2());
        holder1.t2.setText(list.get(position).getC3());
        holder1.t3.setText(list.get(position).getC1());
        holder1.t4.setText(list.get(position).getC4T1());
        holder1.t5.setText(list.get(position).getC4R());
        holder1.t6.setText(list.get(position).getC4T2());


//                dbc=new DB_C(context);
//                list2=new ArrayList<>();
//                list2=dbc.showAll();
        holder1.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnshouCangListerer(v,position,1,list.get(position).getC52Link(), list.get(position).getC2(),list.get(position).getC3(),list.get(position).getC1(),list.get(position).getC4T1(),list.get(position).getC4T2(),list.get(position).getC4R());
//                Toast.makeText(context,"点了收藏"+position,Toast.LENGTH_SHORT).show();
                holder1.imageView4.setVisibility(View.VISIBLE);
                }


            }
        });
        holder1.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnshouCangListerer(v,position,2,list.get(position).getC52Link(), list.get(position).getC2(),list.get(position).getC3(),list.get(position).getC1(),list.get(position).getC4T1(),list.get(position).getC4T2(),list.get(position).getC4R());
//                    Toast.makeText(context,"点了取消收藏"+position,Toast.LENGTH_SHORT).show();
                    holder1.imageView4.setVisibility(View.GONE);
                }
            }
        });


    }
//    public void addShou(){
//        list=new ArrayList<>();
//        list=dbc.showAll();
//        textView= (TextView) findViewById(R.id.two_textview);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("dbc"," list  "+list.size());
//                final String name = intent.getStringExtra("name");
//                final int bianhao = intent.getIntExtra("id", 0);
//                final String url = intent.getStringExtra("url");
//                final String jpg = intent.getStringExtra("jpg");
//                for(User u:list){
//                    if(u.getUser_id()==bianhao){
//                        a++;
//                    }
//                }
//                Log.e("00","   "+a+"  "+bianhao);
//                if (a==0) {
//                    dbc.add(bianhao, url, jpg, name);
//                    adpter = new Adpter(dbc.showAll(), oneActivity.this);
//                    Toast.makeText(oneActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
//                    list = dbc.showAll();
//                    adpter.update(dbc.showAll());
//                } else {
//                    Toast.makeText(oneActivity.this, "收藏失败，该网页你已收藏！", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        Log.e("list","  "+list.size()+"   "+list1.size());
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4,t5,t6;
        ImageView imageView1,imageView2,imageView3,imageView4;
        RelativeLayout cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            t1= (TextView) itemView.findViewById(R.id.adapter_son1_c2);
            t2= (TextView) itemView.findViewById(R.id.adapter_son1_c3);
            t3= (TextView) itemView.findViewById(R.id.adapter_son1_c1);
            t4= (TextView) itemView.findViewById(R.id.adapter_son1_c4T1);
            t5= (TextView) itemView.findViewById(R.id.adapter_son1_c4R);
            t6= (TextView) itemView.findViewById(R.id.adapter_son1_c4T2);
            imageView1= (ImageView) itemView.findViewById(R.id.imageView1);
            imageView2= (ImageView) itemView.findViewById(R.id.imageView2);
            imageView3= (ImageView) itemView.findViewById(R.id.image1_1);
            imageView4= (ImageView) itemView.findViewById(R.id.image1_2);
            cardView= (RelativeLayout) itemView.findViewById(R.id.fragment1_layout);
        }
    }
    //注册接口方法
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    //回调
    //接口
    public interface OnItemClickListener{
        void OnItemClickListener(View view, int position, String url,String time,String time1,String time2,String dui,String dui1);
        void OnshouCangListerer(View view, int position, int image3,String url,String time,String time1,String kaisai,String dui,String dui1,String bifen);
    }
}

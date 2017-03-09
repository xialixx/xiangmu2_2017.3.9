package com.example.administrator.myapplication_class_227.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.user.User3.ResultBean.DataBean;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */
public class MyAdapter3 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<DataBean> list;
    private Context context;
    public MyAdapter3(Context context){
        this.list=new ArrayList<>();
        this.context=context;
    }
    //添加数据
    public void addList( List<DataBean> list){
        if(this.list.containsAll(list)){
            return;
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    //更新数据
    public void updateList( List<DataBean> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder=null;
        if(viewType==1){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item1_1,parent,false);
            holder=new MyViewHolder(view);
        }else if(viewType==2){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item1_2,parent,false);
            holder=new MyViewHolder2(view);
        }else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item1_3,parent,false);
            holder=new MyViewHolder3(view);
        }
        return holder;
    }

    //复写类型设置方法
    @Override
    public int getItemViewType(int position) {
        int i=1;
        if(list.get(position).getThumbnail_pic_s02()!=null){
            i++;
        }
        if (list.get(position).getThumbnail_pic_s03()!=null){
            i++;
        }
        return i;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MyViewHolder ) {
            MyViewHolder holder1= (MyViewHolder) holder;
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.OnItemClickListener(v,position,list.get(position).getUrl());
                    }
                }
            });
            holder1.t1.setText(list.get(position).getTitle());
            holder1.t2.setText(list.get(position).getAuthor_name());
            holder1.t3.setText(list.get(position).getDate());
            Picasso.with(context).load(list.get(position).getThumbnail_pic_s())
                    .into(holder1.imageView);
        }else if(holder instanceof MyViewHolder2){
            MyViewHolder2 holder2= (MyViewHolder2) holder;
            holder2.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.OnItemClickListener(v,position,list.get(position).getUrl());
                    }
                }
            });
            holder2.t1.setText(list.get(position).getTitle());
            holder2.t2.setText(list.get(position).getAuthor_name());
            holder2.t3.setText(list.get(position).getDate());
            Picasso.with(context).load(list.get(position).getThumbnail_pic_s())
                    .into(holder2.imageView1);
            Picasso.with(context).load(list.get(position).getThumbnail_pic_s02())
                    .into(holder2.imageView2);
            Picasso.with(context).load(list.get(position).getThumbnail_pic_s03())
                    .into(holder2.imageView3);
        }else {
            MyViewHolder3 holder3= (MyViewHolder3) holder;
            holder3.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.OnItemClickListener(v,position,list.get(position).getUrl());
                    }
                }
            });
            holder3.t1.setText(list.get(position).getTitle());
            holder3.t2.setText(list.get(position).getAuthor_name());
            holder3.t3.setText(list.get(position).getDate());
            Picasso.with(context).load(list.get(position).getThumbnail_pic_s())
                    .into(holder3.imageView1);
            Picasso.with(context).load(list.get(position).getThumbnail_pic_s02())
                    .into(holder3.imageView2);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            t1= (TextView) itemView.findViewById(R.id.item1_1_tv1);
            t2= (TextView) itemView.findViewById(R.id.item1_1_tv2);
            t3= (TextView) itemView.findViewById(R.id.item1_1_tv3);
            imageView= (ImageView) itemView.findViewById(R.id.item1_1_im1);
            cardView= (CardView) itemView.findViewById(R.id.item1_cardview);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder{
        TextView t1,t2,t3;
        ImageView imageView1,imageView2,imageView3;
        CardView cardView;
        public MyViewHolder2(View itemView) {
            super(itemView);
            t1= (TextView) itemView.findViewById(R.id.item1_2_tv1);
            t2= (TextView) itemView.findViewById(R.id.item1_2_tv2);
            t3= (TextView) itemView.findViewById(R.id.item1_2_tv3);
            imageView1= (ImageView) itemView.findViewById(R.id.item1_2_im1);
            imageView2= (ImageView) itemView.findViewById(R.id.item1_2_im2);
            imageView3= (ImageView) itemView.findViewById(R.id.item1_2_im3);
            cardView= (CardView) itemView.findViewById(R.id.item2_cardview);
        }
    }
    class MyViewHolder3 extends RecyclerView.ViewHolder{
        TextView t1,t2,t3;
        ImageView imageView1,imageView2;
        CardView cardView;
        public MyViewHolder3(View itemView) {
            super(itemView);
            t1= (TextView) itemView.findViewById(R.id.item1_3_tv1);
            t2= (TextView) itemView.findViewById(R.id.item1_3_tv2);
            t3= (TextView) itemView.findViewById(R.id.item1_3_tv3);
            imageView1= (ImageView) itemView.findViewById(R.id.item1_3_im1);
            imageView2= (ImageView) itemView.findViewById(R.id.item1_3_im2);
            cardView= (CardView) itemView.findViewById(R.id.item3_cardview);
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
        void OnItemClickListener(View view, int position, String url);
    }
}

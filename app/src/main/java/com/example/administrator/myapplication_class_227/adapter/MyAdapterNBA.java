package com.example.administrator.myapplication_class_227.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.fragment.Myfragment1_son;
import com.example.administrator.myapplication_class_227.user.UserNBA;
import com.example.administrator.myapplication_class_227.user.UserNBA.ResultBean.ListBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */
public class MyAdapterNBA extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private ArrayList<ListBean> list;
    private Context context;
    public MyAdapterNBA(Context context){
        this.list=new ArrayList<>();
        this.context=context;
    }
    //添加数据
    public void addList(List<ListBean> list){
        if(this.list.containsAll((ArrayList)list)){
            return;
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    //更新数据
    public void updateList(List<ListBean> list){
        this.list=(ArrayList) list;
        notifyDataSetChanged();
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder=null;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fragment2_adapter_son,null);
        holder=new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        holder1.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClickListener(v,position,list.get(position).getTr().get(position).getLink1url());
                }
            }
        });
        holder1.t1.setText(list.get(position).getTr().get(position).getTime());
        holder1.t2.setText(list.get(position).getTr().get(position).getPlayer1());
        holder1.t3.setText(list.get(position).getTr().get(position).getScore());
        holder1.t4.setText(list.get(position).getTr().get(position).getPlayer2());
        Picasso.with(context).load(list.get(position).getTr().get(position).getPlayer1logobig())
                .into(holder1.imageView1);
        Picasso.with(context).load(list.get(position).getTr().get(position).getPlayer2logobig())
                .into(holder1.imageView2);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4;
        ImageView imageView1,imageView2;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
           t1= (TextView) itemView.findViewById(R.id.adapter_son2_c3);
            t2= (TextView) itemView.findViewById(R.id.adapter_son2_c4T1);
            t3= (TextView) itemView.findViewById(R.id.adapter_son2_c4R);
            t4= (TextView) itemView.findViewById(R.id.adapter_son2_c4T2);
            imageView1= (ImageView) itemView.findViewById(R.id.imageView_adapter2);
            imageView2= (ImageView) itemView.findViewById(R.id.imageView_adapter22);
            cardView= (CardView) itemView.findViewById(R.id.fragment2_cardview);
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

package com.example.administrator.myapplication_class_227.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.myapplication_class_227.R;
import com.example.administrator.myapplication_class_227.fragment.Myfragment1_son;
import com.example.administrator.myapplication_class_227.user.User.ResultBean.ViewsBean.JifenbangBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */
public class RecycleviewAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Myfragment1_son myfragment1_son=new Myfragment1_son();
    private ArrayList<JifenbangBean> list;
    private Context context;
    public RecycleviewAdapter2(Context context){
        this.list=new ArrayList<>();
        this.context=context;
    }
    //添加数据
    public void addList(List<JifenbangBean> list){
        if(this.list.containsAll((ArrayList)list)){
            return;
        }
        this.list.addAll((ArrayList)list);
        notifyDataSetChanged();
    }
    //更新数据
    public void updateList(List<JifenbangBean> list){
        this.list=(ArrayList)list;
        notifyDataSetChanged();
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder=null;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_jifenbangbean_adapter,null);
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
                    listener.OnItemClickListener(v,position,list.get(position).getC2L());
                }
            }
        });
        if(list.get(position).getC1().equals("1")||list.get(position).getC1().equals("2")||list.get(position).getC1().equals("3")){
            holder1.t1.setTextColor(0xFFF90000);
            holder1.t1.setText(list.get(position).getC1());
        }else if(list.get(position).getC1().equals("18")||list.get(position).getC1().equals("19")||list.get(position).getC1().equals("20")){
           holder1.t1.setTextColor(0xFF00FD50);
            holder1.t1.setText(list.get(position).getC1());
        }else{
            holder1.t1.setText(list.get(position).getC1());}
        holder1.t2.setText(list.get(position).getC2());
        holder1.t3.setText(list.get(position).getC3());
        holder1.t4.setText(list.get(position).getC41()+"-"+list.get(position).getC42()+"-"+list.get(position).getC43());
        holder1.t5.setText(list.get(position).getC5());
        holder1.t6.setText(list.get(position).getC6());
    }

    @Override
    public int getItemCount() {
        Log.e("list",list.size()+"  ");
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3,t4,t5,t6;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            t1= (TextView) itemView.findViewById(R.id.jifenbang_textview1);
            t2= (TextView) itemView.findViewById(R.id.jifenbang_textview2);
            t3= (TextView) itemView.findViewById(R.id.jifenbang_textview3);
            t4= (TextView) itemView.findViewById(R.id.jifenbang_textview4);
            t5= (TextView) itemView.findViewById(R.id.jifenbang_textview5);
            t6= (TextView) itemView.findViewById(R.id.jifenbang_textview6);
            cardView= (CardView) itemView.findViewById(R.id.fragmentjifen2_cardview);
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

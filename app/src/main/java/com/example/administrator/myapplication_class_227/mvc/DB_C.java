package com.example.administrator.myapplication_class_227.mvc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.myapplication_class_227.user.User.ResultBean.ViewsBean.Saicheng1Bean;
import com.example.administrator.myapplication_class_227.user.shoucangUser;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/23.
 */
public class DB_C {
    private SQLiteDatabase db;
    public DB_C(Context context){
        db=new DB_M(context).getWritableDatabase();
    }
    public void  add(int ischeak, String c2, String c3, String c1, String c4T1, String c4R, String c4T2,String c52Link){
        String sql="insert into "+ DB_M.one +" values (null,' "+ischeak
                +" ','"+c2+" ','"+c3+" ','"+c1+" ','"+c4T1+" ','"+c4R+" ','"+c4T2+" ','"+c52Link+" ')";
        db.execSQL(sql);
    }
    public void deleteAll(ArrayList<Saicheng1Bean> list){
        for(Saicheng1Bean u:list) {
            Log.e("oooo","   "+u.toString());
            Log.e("g","wwww"+u.getC4T1()+"   "+u.getC4T2());
            if(u.getIscheak()==1) {
                Log.e("12345667","   "+u.toString());
                String sql="delete from " + DB_M.one + " where C4T1='"+u.getC4T1()+"'  and  C4T2='"+u.getC4T2()+"'";
                Log.e("g","zzzzz"+sql);
                db.execSQL(sql);
            }
        }
    }

    /**
     * 查看所有数据
     */
    public ArrayList<Saicheng1Bean> showAll(){
        ArrayList<Saicheng1Bean> list=new ArrayList<Saicheng1Bean>();
        String sql="select * from " +DB_M.one ;
        Log.e("msg","qq"+DB_M.one);
        Cursor cursor= db.rawQuery(sql,null);
        while(cursor.moveToNext()){
//             User user =new User();
//             String name=cursor.getString(cursor.getColumnIndex("name"));
//            user.setName(name);
//            user.setName(cursor.getString(cursor.getColumnIndex("name")));
//            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
//            User user =new User(cursor.getString(cursor.getColumnIndex("name")),
//                    cursor.getString(cursor.getColumnIndex("password")));

//            cursor.getString(cursor.getColumnIndex("ischeak")),
            list.add(new Saicheng1Bean(cursor.getInt(cursor.getColumnIndex("ischeak")),
                    cursor.getString(cursor.getColumnIndex("c2")),
                    cursor.getString(cursor.getColumnIndex("c3")),
                    cursor.getString(cursor.getColumnIndex("c1")),
                    cursor.getString(cursor.getColumnIndex("c4T1")),
                    cursor.getString(cursor.getColumnIndex("c4R")),
                    cursor.getString(cursor.getColumnIndex("c4T2")),
                    cursor.getString(cursor.getColumnIndex("c52Link"))));
        }
        cursor.close();
        return list;
    }
}

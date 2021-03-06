package com.cnitpm.financial.Util;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Movie;
import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**数据库操作类**/
public class SqlOperation  {
    /**
     * 添加数据
     * @param litePalSupport
     * @return
     */
    public boolean Insert(LitePalSupport litePalSupport){
        return litePalSupport.save();
    }

    /**
     * 查询全部
     * @param c
     * @return
     */
    public List SelectAll(Class c){
        return LitePal.findAll(c);
    }

    /**
     * 查询指定id
     * @param c
     * @return
     */
    public Object SelectId(Class c,int id){
        return LitePal.find(c,id);
    }

    /**
     * 查询多个指定id
     * @param c
     * @return
     */
    public List SelectIds(Class c,long[] ids){
        return LitePal.findAll(c,ids);
    }

    /**
     * 条件查询
     * @param c
     * @return
     */
    public List SelectWhere(Class c,String... conditions){
        return LitePal.where(conditions).find(c);
    }

    /**
     * sql 查询
     * @return
     */
    public  List<String> SelectSql(String... conditions){
        Cursor cursor= LitePal.findBySQL(conditions);
        List<String> list=new ArrayList<>();
        while (cursor.moveToNext()){
                list.add(cursor.getString(cursor.getColumnIndex("A"))+"#"+cursor.getString(cursor.getColumnIndex("B")));
        }
        return list;
    }

    /**
     * sql 删除
     * @return
     */
    public  boolean DeleteSql(Class c,int id){
        if(LitePal.delete(c,id)>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * sql 修改
     * @return
     */
    public  boolean UpdateSql(Class c, ContentValues values, int id){
        if(LitePal.update(c,values,id)>0){
            return true;
        }else {
            return false;
        }
    }
}

package com.cnitpm.financial.Util;

import android.graphics.Movie;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

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
}

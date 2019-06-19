package com.cnitpm.financial.Model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 所有数据模型
 * @param <T>
 */
public class AllModel<T> implements MultiItemEntity {
    private int state;   //代码
    private String message;  //内容
    private T data;   //数据
    private int itemType;   //recycler 的布局

    public AllModel(T data,int itemType){
        this.itemType=itemType;
        this.data=data;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}

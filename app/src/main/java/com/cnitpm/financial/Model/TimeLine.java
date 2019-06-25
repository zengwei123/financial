package com.cnitpm.financial.Model;

import android.graphics.Bitmap;

import org.litepal.crud.LitePalSupport;
import org.litepal.parser.LitePalParser;

/**
 * 首页是时光轴的数据对象
 */
public class TimeLine extends LitePalSupport {
    private int id;  //id
    private Boolean Direction=true;   //方向  True=左（收入）   false=右（支出）
    private int Icon_Class=0;   //时间轴图标和类型下标
    private String Message=null; //详细内容
    private String ImageUrl;
    private String Time=null;   //日期
    private double Price=0;   //价格
    private int NoteBook=0;   //账本id


    public TimeLine() {
    }


    public TimeLine(Boolean direction, int icon_Class, String message, String imageUrl, String time, double price, int noteBook) {
        Direction = direction;
        Icon_Class = icon_Class;
        Message = message;
        ImageUrl = imageUrl;
        Time = time;
        Price = price;
        NoteBook = noteBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Boolean getDirection() {
        return Direction;
    }

    public void setDirection(Boolean direction) {
        Direction = direction;
    }

    public int getIcon_Class() {
        return Icon_Class;
    }

    public void setIcon_Class(int icon_Class) {
        Icon_Class = icon_Class;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getNoteBook() {
        return NoteBook;
    }

    public void setNoteBook(int noteBook) {
        NoteBook = noteBook;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "id:"+id+
                "Direction=" + Direction +
                ", Icon_Class=" + Icon_Class +
                ", Message='" + Message + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", Time='" + Time + '\'' +
                ", Price=" + Price +
                ", NoteBook=" + NoteBook +
                '}';
    }
}

package com.cnitpm.financial.Model;

/**
 * 日历页面的数据对象
 * 数据更TimeLine 相同 建一个新的主要是recyclerView 的布局不用  需要区分
 */
public class CalendarRecord {
    private Boolean Direction=true;   //方向  True=左（收入）   false=右（支出）
    private int Icon_Class=0;   //时间轴图标和类型下标
    private String Message=null; //详细内容
    private String Time=null;   //日期
    private double Price=0;   //价格
    private int NoteBook=0;   //账本id

    public CalendarRecord(Boolean direction, int icon_Class, String message, String time, double price,int NoteBook) {
        this.Direction = direction;
        this.Icon_Class = icon_Class;
        this.Message = message;
        this.Time = time;
        this.Price = price;
        this.NoteBook=NoteBook;
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
}

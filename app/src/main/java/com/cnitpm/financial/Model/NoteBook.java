package com.cnitpm.financial.Model;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class NoteBook extends LitePalSupport {
    private int id;
    @Column(unique = true)
    private String NoteBookName;
    private String Date;   //创建日期

    public NoteBook(int id, String noteBookName,String Date) {
        this.id = id;
        this.NoteBookName = noteBookName;
        this.Date=Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteBookName() {
        return NoteBookName;
    }

    public void setNoteBookName(String noteBookName) {
        NoteBookName = noteBookName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id=" + id +
                ", NoteBookName='" + NoteBookName + '\'' +
                '}';
    }
}

package com.cnitpm.financial.Model;


import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class NoteBook extends LitePalSupport implements Serializable {
    private int id;
    @Column(unique = true)
    private String NoteBookName;
    private String Date;   //创建日期
    private double Budget;

    public NoteBook(int id, String noteBookName, String date, double budget) {
        this.id = id;
        NoteBookName = noteBookName;
        Date = date;
        Budget = budget;
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

    public double getBudget() {
        return Budget;
    }

    public void setBudget(double budget) {
        Budget = budget;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "id=" + id +
                ", NoteBookName='" + NoteBookName + '\'' +
                ", Date='" + Date + '\'' +
                ", Budget=" + Budget +
                '}';
    }
}

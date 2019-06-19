package com.cnitpm.financial.Model;

/**
 * 图片和类型的对象
 */
public class CIModel {
    private int icon;
    private String aclass;

    public CIModel(int icon, String aclass) {
        this.icon = icon;
        this.aclass = aclass;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getAclass() {
        return aclass;
    }

    public void setAclass(String aclass) {
        this.aclass = aclass;
    }
}

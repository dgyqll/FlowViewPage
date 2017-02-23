package com.etouse.flowviewpage.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PayTypeBean implements Serializable{
    int id;
    String name;
    String used;

    public PayTypeBean() {

    }

    public PayTypeBean(int id, String name, String used) {
        this.id = id;
        this.name = name;
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsed() {
        return used;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsed(String used) {
        this.used = used;
    }
}

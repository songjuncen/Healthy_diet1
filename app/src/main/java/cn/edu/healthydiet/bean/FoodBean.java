package cn.edu.healthydiet.bean;

import java.io.Serializable;
//代表foodbean可以序列化



public class FoodBean implements Serializable {
    private  String  title;
    private String notmatch;
    private  String desc;//食物信息
    private  int picId;//图片显示
    //关于条目所显示的信息都展示在这个对象当中

    public FoodBean(String title,String notmatch,String desc,int picId) {
        this.title = title;
        this.notmatch = notmatch;
        this.desc = desc;
        this.picId = picId;
    }

    public FoodBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotmatch() {
        return notmatch;
    }

    public void setNotmatch(String notmatch) {
        this.notmatch = notmatch;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}

package com.zoopark.demo.notify.bean;

public class DecorationBean {

    private String title;
    private String btnTitle;

    public DecorationBean(String title, String btnTitle) {
        this.title = title;
        this.btnTitle = btnTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBtnTitle() {
        return btnTitle;
    }

    public void setBtnTitle(String btnTitle) {
        this.btnTitle = btnTitle;
    }
}

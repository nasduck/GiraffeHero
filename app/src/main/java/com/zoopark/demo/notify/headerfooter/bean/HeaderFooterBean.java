package com.zoopark.demo.notify.headerfooter.bean;

public class HeaderFooterBean {

    private String title;
    private String btnTitle;

    public HeaderFooterBean(String title, String btnTitle) {
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

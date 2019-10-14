package com.zoopark.demo.complex.bean;

public class ItemBean {

    private Integer imageId;
    private Integer content;

    public ItemBean(Integer imageId, Integer content) {
        this.imageId = imageId;
        this.content = content;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }
}

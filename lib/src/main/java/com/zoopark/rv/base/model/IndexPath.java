package com.zoopark.rv.base.model;

public class IndexPath {

    private int mSection;
    private int mRow;
    private int mProviderSection; // Only work with header/footer

    public IndexPath() {}

    public IndexPath(int section, int row) {
        this.mSection = section;
        this.mRow = row;
    }

    public int getSection() {
        return mSection;
    }

    public void setSection(int section) {
        this.mSection = section;
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        this.mRow = row;
    }

    public int getProviderSection() {
        return mProviderSection;
    }

    public void setProviderSection(int providerSection) {
        this.mProviderSection = providerSection;
    }

    @Override
    public String toString() {
        return "IndexPath{" +
                "mSection=" + mSection +
                ", mRow=" + mRow +
                ", mProviderSection=" + mProviderSection +
                '}';
    }
}
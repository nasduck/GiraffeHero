package com.nasduck.lib;

public class IndexPath {

    private int mSection;
    private int mRow;

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

    @Override
    public String toString() {
        return "IndexPath{" +
                "mSection=" + mSection +
                ", mRow=" + mRow +
                '}';
    }
}
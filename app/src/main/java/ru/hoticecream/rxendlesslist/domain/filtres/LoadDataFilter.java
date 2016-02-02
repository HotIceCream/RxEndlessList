package ru.hoticecream.rxendlesslist.domain.filtres;

public class LoadDataFilter {
    private int offset;

    public LoadDataFilter() {
        this(0);
    }

    public LoadDataFilter(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }
}

package ru.hoticecream.rxendlesslist.presentation.endlesslist;

import java.util.List;

import ru.hoticecream.rxendlesslist.data.DataItem;

public interface EndlessListView {
    void renderItems(List<DataItem> dataItems);
}

package ru.hoticecream.rxendlesslist.domain.storages;

import java.util.List;

import ru.hoticecream.rxendlesslist.data.DataItem;
import rx.Observable;

public interface DataStorage {

    Observable<List<DataItem>> loadData();

    Observable<List<DataItem>> loadData(int offset);

    Observable<List<DataItem>> loadData(int offset, int limit);
}

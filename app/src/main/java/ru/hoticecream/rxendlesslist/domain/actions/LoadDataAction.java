package ru.hoticecream.rxendlesslist.domain.actions;

import java.util.List;

import ru.hoticecream.rxendlesslist.data.DataItem;
import ru.hoticecream.rxendlesslist.domain.filtres.LoadDataFilter;
import ru.hoticecream.rxendlesslist.domain.storages.DataStorage;
import rx.Observable;

public class LoadDataAction extends BaseAction<List<DataItem>, LoadDataFilter> {

    private final DataStorage storage;

    public LoadDataAction(DataStorage storage) {
        this.storage = storage;
    }

    @Override
    public Observable<? extends List<DataItem>> buildObservable(LoadDataFilter loadDataFilter) {
        return storage.loadData(loadDataFilter.getOffset());
    }
}

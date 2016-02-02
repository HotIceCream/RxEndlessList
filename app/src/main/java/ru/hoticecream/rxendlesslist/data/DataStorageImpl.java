package ru.hoticecream.rxendlesslist.data;

import java.util.List;

import ru.hoticecream.rxendlesslist.domain.storages.DataStorage;
import rx.Observable;

public class DataStorageImpl implements DataStorage {
    private static final int DEFAULT_LIMIT = 10;

    @Override
    public Observable<List<DataItem>> loadData() {
        return loadData(0);
    }

    @Override
    public Observable<List<DataItem>> loadData(int offset) {
        return loadData(offset, DEFAULT_LIMIT);
    }

    @Override
    public Observable<List<DataItem>> loadData(final int offset, int limit) {
        return Observable
                .create(subscriber -> {
                    int id = offset + 1;
                    while (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(id);
                        id++;
                    }
                    subscriber.onCompleted();

                })
                .take(limit)
                .map(integer -> new DataItem((Integer) integer, "Message "))
                .toList();
    }
}

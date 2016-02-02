package ru.hoticecream.rxendlesslist.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.hoticecream.rxendlesslist.data.DataStorageImpl;
import ru.hoticecream.rxendlesslist.domain.storages.DataStorage;

@Module
public class DataModule {

    @Provides
    @Singleton
    public DataStorage provideDataStorage() {
        return new DataStorageImpl();
    }
}

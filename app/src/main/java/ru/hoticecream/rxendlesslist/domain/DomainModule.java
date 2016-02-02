package ru.hoticecream.rxendlesslist.domain;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.hoticecream.rxendlesslist.data.DataItem;
import ru.hoticecream.rxendlesslist.domain.actions.LoadDataAction;
import ru.hoticecream.rxendlesslist.domain.filtres.LoadDataFilter;
import ru.hoticecream.rxendlesslist.domain.storages.DataStorage;

@Module
public class DomainModule {

    @Provides
    @Singleton
    public LoadDataAction provideLoadDataAction(DataStorage storage) {
        return new LoadDataAction(storage);
    }

    @Provides
    @Named(Interactor.LOAD_DATA)
    public Interactor<List<DataItem>, LoadDataFilter> provideLoadDataInteractor(LoadDataAction action) {
        return new Interactor<>(action);
    }

}

package ru.hoticecream.rxendlesslist.presentation.endlesslist;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.hoticecream.rxendlesslist.AppComponent;
import ru.hoticecream.rxendlesslist.data.DataItem;
import ru.hoticecream.rxendlesslist.domain.Interactor;
import ru.hoticecream.rxendlesslist.domain.filtres.LoadDataFilter;
import rx.Subscriber;

public class EndlessListPresenter {
    private EndlessListActivity view;

    @Inject
    @Named(Interactor.LOAD_DATA)
    Interactor<List<DataItem>, LoadDataFilter> loadDataInteractor;

    public EndlessListPresenter() {
        AppComponent.Holder.get().inject(this);
    }

    public void setView(EndlessListActivity view) {
        this.view = view;
    }

    public EndlessListView getView() {
        return view;
    }

    public void onStart() {
        loadDataInteractor.execute(new Subscriber<List<DataItem>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<DataItem> dataItems) {
                renderData(dataItems);
            }
        });
    }

    public void onStop() {
        loadDataInteractor.unsubscribe();
    }

    public void loadMore(int offset) {
        loadDataInteractor.updateParameter(new LoadDataFilter(offset));
    }

    private void renderData(List<DataItem> dataItems) {
        getView().renderItems(dataItems);
    }
}

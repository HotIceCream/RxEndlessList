package ru.hoticecream.rxendlesslist;

import javax.inject.Singleton;

import dagger.Component;
import ru.hoticecream.rxendlesslist.data.DataModule;
import ru.hoticecream.rxendlesslist.domain.DomainModule;
import ru.hoticecream.rxendlesslist.presentation.PresentationModule;
import ru.hoticecream.rxendlesslist.presentation.endlesslist.EndlessListActivity;
import ru.hoticecream.rxendlesslist.presentation.endlesslist.EndlessListPresenter;

@Singleton
@Component(modules = {DataModule.class, DomainModule.class, PresentationModule.class})
public interface AppComponent {

    void inject(EndlessListActivity endlessListActivity);

    void inject(EndlessListPresenter endlessListPresenter);

    class Holder {
        private static AppComponent instance;

        public static void set(AppComponent component) {
            Holder.instance = component;
        }

        public static AppComponent get() {
            return Holder.instance;
        }
    }


}

package ru.hoticecream.rxendlesslist;

import android.app.Application;

import ru.hoticecream.rxendlesslist.data.DataModule;
import ru.hoticecream.rxendlesslist.domain.DomainModule;
import ru.hoticecream.rxendlesslist.presentation.PresentationModule;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent.Holder.set(DaggerAppComponent.builder()
                .dataModule(new DataModule())
                .domainModule(new DomainModule())
                .presentationModule(new PresentationModule())
                .build());
    }
}

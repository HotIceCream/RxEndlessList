package ru.hoticecream.rxendlesslist.presentation;

import dagger.Module;
import dagger.Provides;
import ru.hoticecream.rxendlesslist.presentation.endlesslist.EndlessListPresenter;

@Module
public class PresentationModule {

    @Provides
    public EndlessListPresenter provideEndlessListPresenter() {
        return new EndlessListPresenter();
    }
}

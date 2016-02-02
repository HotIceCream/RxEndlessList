package ru.hoticecream.rxendlesslist.domain.actions;

import rx.Observable;

public abstract class BaseAction<ResultType, ParameterType> {
    public abstract Observable<? extends ResultType> buildObservable(ParameterType parameterType);
}

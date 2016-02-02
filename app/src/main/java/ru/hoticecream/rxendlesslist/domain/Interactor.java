package ru.hoticecream.rxendlesslist.domain;

import ru.hoticecream.rxendlesslist.domain.actions.BaseAction;
import rx.Subscriber;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

public class Interactor<ResultType, ParameterType> {

    public static final String LOAD_DATA = "load_data";

    private final BaseAction<ResultType, ParameterType> action;
    private final BehaviorSubject<ParameterType> subject = BehaviorSubject.create();
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public Interactor(BaseAction<ResultType, ParameterType> action) {
        this.action = action;
    }

    public void execute(Subscriber<ResultType> subscriber) {
        subscriptions.add(subject.flatMap(action::buildObservable).subscribe(subscriber));
    }

    public void updateParameter(ParameterType param) {
        subject.onNext(param);
    }

    public void unsubscribe() {
        if (!subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
        subscriptions = new CompositeSubscription();
    }
}

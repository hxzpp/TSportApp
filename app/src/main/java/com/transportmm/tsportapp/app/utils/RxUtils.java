package com.transportmm.tsportapp.app.utils;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.xinhuamm.xinhuasdk.mvp.IView;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RxUtils {

    private RxUtils() {

    }

    public static <T> ObservableTransformer<T, T> applySchedulers(final IView view) {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterTerminate(new Action() {
                            @Override
                            public void run() {
                            }
                        }).compose(RxUtils.bindToLifecycle(view));
            }
        };
    }


    public static <T> LifecycleTransformer<T> bindToLifecycle(IView view) {
        if (view instanceof LifecycleProvider){
            return ((LifecycleProvider)view).bindToLifecycle();
        }else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }

    }

}

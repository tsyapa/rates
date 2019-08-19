package com.tsyapa.domain.interactor.base;

import com.tsyapa.domain.executor.PostExecutionThread;
import com.tsyapa.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BaseInteractor {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private CompositeDisposable compositeDisposable;

    @Inject
    public BaseInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    protected <Model> void execute(Observable<Model> observable, DisposableObserver<Model> observer) {
        observable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.provideScheduler())
                .doOnSubscribe(this::addDisposable)
                .subscribe(observer);
    }

    private void addDisposable(Disposable disposable) { compositeDisposable.add(disposable); }

    public void dispose() { compositeDisposable.clear(); }
}
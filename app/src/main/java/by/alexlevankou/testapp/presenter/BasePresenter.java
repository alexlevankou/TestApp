package by.alexlevankou.testapp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import by.alexlevankou.testapp.App;
import by.alexlevankou.testapp.model.Repository;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<View> extends ViewModel implements LifecycleObserver, BaseContract.Presenter {

    @Inject
    protected Repository repository;

    protected CompositeDisposable disposables;
    protected View view = null;
    private Lifecycle viewLifecycle = null;

    public void attachView(View view, Lifecycle viewLifecycle) {
        this.view = view;
        this.viewLifecycle = viewLifecycle;
        viewLifecycle.addObserver(this);
        disposables = new CompositeDisposable();

    }

    protected View view() {
        return view;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onViewDestroyed() {
        view = null;
        viewLifecycle = null;
        if(disposables != null) {
            disposables.dispose();
        }
    }
}
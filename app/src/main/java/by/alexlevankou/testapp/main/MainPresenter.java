package by.alexlevankou.testapp.main;

import java.util.List;

import by.alexlevankou.testapp.model.DataEntity;
import by.alexlevankou.testapp.presenter.BasePresenter;
import by.alexlevankou.testapp.presenter.BaseContract;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<BaseContract.View> {

    @Override
    public void getAllEntities() {
//        Disposable disposable = model.getAllEntities()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<DataEntity>>() {
//                    @Override
//                    public void accept(List<DataEntity> entities) throws Exception {
//                        view.hideLoading();
//                        if(entities != null && entities.size() > 0) {
//                            view.updateList(entities);
//                        } else {
//                            view.showNoDataText();
//                        }
//                    }
//                });
//        disposables.add(disposable);
    }

    @Override
    public void addEntity() {

        DataEntity entity = new DataEntity();
        // randomize
        entity.setUserId(4);
        entity.setName("John");
        entity.setBody("Raven");
        entity.setNumber(4.45);

        // model.addEntity
        //Observable.fromCallable(() -> db.countriesDao().addCountries())

    }
}

package by.alexlevankou.testapp.main;

import java.util.List;
import java.util.Random;

import by.alexlevankou.testapp.App;
import by.alexlevankou.testapp.model.DataEntity;
import by.alexlevankou.testapp.presenter.BaseContract;
import by.alexlevankou.testapp.presenter.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<BaseContract.View> {


    @Override
    public void getAllEntities() {
        App.getComponent().inject(this);

        Disposable disposable = repository.getAllEntities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DataEntity>>() {
                    @Override
                    public void accept(List<DataEntity> entities) throws Exception {
                        view.hideLoading();
                        if(entities != null && entities.size() > 0) {
                            view.updateList(entities);
                        } else {
                            view.showNoDataText();
                        }
                    }
                });
        disposables.add(disposable);
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

    private DataEntity generateRandomEntity(){
        DataEntity entity = new DataEntity();

        return entity;
    }

    private int getRandomInt(){
        final int MIN = 0;
        final int MAX = 100;
        Random r = new Random();
        return r.nextInt((MAX - MIN) + 1) + MIN;
    }

    private double getRandomDouble(){
        final double MIN = 0.0;
        final double MAX = 100.0;
        Random r = new Random();
        return r.nextDouble()*MAX + MIN;
    }


//    private String getRandomString(){
//        final double MIN = 0.0;
//        final double MAX = 100.0;
//        Random r = new Random();
//        return r.nextDouble()*MAX + MIN;
//    }
}

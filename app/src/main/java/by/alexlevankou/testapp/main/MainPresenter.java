package by.alexlevankou.testapp.main;

import java.util.List;
import java.util.Random;

import by.alexlevankou.testapp.App;
import by.alexlevankou.testapp.model.DataEntity;
import by.alexlevankou.testapp.presenter.BaseContract;
import by.alexlevankou.testapp.presenter.BasePresenter;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<BaseContract.View> {


    @Override
    public void search(String query) {
        if(query.length() > 0){
            Disposable disposable = repository.getSearchResults(query)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<DataEntity>>() {
                        @Override
                        public void accept(List<DataEntity> entities) throws Exception {
                            if(entities != null && entities.size() > 0) {
                                view.updateList(entities);
                            } else {
                                view.showNoDataText();
                            }
                        }
                    });
            disposables.add(disposable);
        } else {
            getAllEntities();
        }

    }

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
        Completable.fromAction(() -> repository
                .addEntity(generateRandomEntity()))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private DataEntity generateRandomEntity(){
        DataEntity entity = new DataEntity();
        Random rnd = new Random();
        entity.setUserId(rnd.nextInt(10));
        entity.setNumber(rnd.nextDouble()*100);
        entity.setName(getRandomString(5));
        entity.setBody(getRandomString(10));
        return entity;
    }

    private String getRandomString(int length){
        final String charArray = "abcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            stringBuilder.append(charArray.charAt(r.nextInt(charArray.length())));
        }
        return stringBuilder.toString();
    }
}

package by.alexlevankou.testapp.mainscreen;

import java.util.List;
import java.util.Random;

import by.alexlevankou.testapp.App;
import by.alexlevankou.testapp.database.DataEntity;
import by.alexlevankou.testapp.basemvp.BaseContract;
import by.alexlevankou.testapp.basemvp.BasePresenter;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<BaseContract.BaseView> {

    @Override
    public void getRepository(){
        App.getComponent().inject(this);
    }

    @Override
    public void search(String query) {
        if(query.length() > 0){
            getSearchEntities(query);
        } else {
            getAllEntities();
        }
    }

    @Override
    public void getEntities(Flowable<List<DataEntity>> flowable){
        view.showLoading();
        Disposable disposable = flowable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(entities -> {
                    view.hideLoading();
                    updateList(entities);
                });
        disposables.add(disposable);
    }

    @Override
    public void getAllEntities() {
        getEntities(repository.getAllEntities());
    }


    @Override
    public void getSearchEntities(String search){
        getEntities(repository.getSearchResults(search));
    }

    @Override
    public void updateList(List<DataEntity> entities){
        if(entities != null && entities.size() > 0) {
            view.updateList(entities);
        } else {
            view.showNoDataText();
        }
    }

    @Override
    public void onSearchStart(){
        view.startSearch();
    }

    @Override
    public void onSearchEnd(){
        getAllEntities();
        view.endSearch();
    }

    @Override
    public void onSearchSubmit(){
        view.endSearch();
    }

    @Override
    public void addEntity() {
        Disposable disposable = Completable.fromAction(() -> repository
                .addEntity(generateRandomEntity()))
                .subscribeOn(Schedulers.io())
                .subscribe( () -> { view.fabClicked(); });
        disposables.add(disposable);
    }

    private DataEntity generateRandomEntity(){
        DataEntity entity = new DataEntity();
        Random rnd = new Random();
        entity.setUserId(rnd.nextInt(10));
        entity.setNumber(rnd.nextDouble()*100);
        entity.setName(getRandomString(rnd, 5));
        entity.setBody(getRandomString(rnd, 10));
        return entity;
    }

    private String getRandomString(Random rnd, int length){
        final String charArray = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder(length);
        for(int i = 0; i < length; i++){
            stringBuilder.append(charArray.charAt(rnd.nextInt(charArray.length())));
        }
        return stringBuilder.toString();
    }
}

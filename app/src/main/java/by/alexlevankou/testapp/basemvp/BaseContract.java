package by.alexlevankou.testapp.basemvp;

import java.util.List;

import by.alexlevankou.testapp.database.DataEntity;
import io.reactivex.Flowable;

public class BaseContract {

    public interface BaseView {
        void updateList(List<DataEntity> entities);
        void showLoading();
        void hideLoading();
        void showNoDataText();
        void startSearch();
        void endSearch();
        void fabClicked();
    }

    public interface BasePresenter {
        void getRepository();
        void addEntity();
        void getEntities(Flowable<List<DataEntity>> flowable);
        void getAllEntities();
        void getSearchEntities(String search);
        void search(String search);
        void updateList(List<DataEntity> entities);
        void onSearchStart();
        void onSearchEnd();
        void onSearchSubmit();
    }

    public interface BaseModel {
        void addEntity(DataEntity dataEntity);
        Flowable<List<DataEntity>> getAllEntities();
        Flowable<List<DataEntity>> getSearchResults(String search);
    }
}

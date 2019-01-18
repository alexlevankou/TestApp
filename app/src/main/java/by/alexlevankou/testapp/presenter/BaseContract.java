package by.alexlevankou.testapp.presenter;

import android.arch.lifecycle.Lifecycle;

import java.util.List;

import by.alexlevankou.testapp.model.DataEntity;
import io.reactivex.Flowable;

public class BaseContract {

    public interface View {
        void updateList(List<DataEntity> entities);
        void showLoading();
        void hideLoading();
        void showNoDataText();
        void endSearch();

    }

    public interface Presenter {
        void getRepository();
        void addEntity();
        void getEntities(Flowable<List<DataEntity>> flowable);
        void getAllEntities();
        void getSearchEntities(String search);
        void search(String search);
        void updateList(List<DataEntity> entities);

        void onSearchEnd();
    }

    public interface Model {
        void addEntity(DataEntity dataEntity);
        Flowable<List<DataEntity>> getAllEntities();
        Flowable<List<DataEntity>> getSearchResults(String search);
    }
}

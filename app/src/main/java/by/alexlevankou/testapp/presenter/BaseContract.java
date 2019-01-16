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

    }

    public interface Presenter {
        void attachView(View view, Lifecycle viewLifecycle);
        void addEntity();
        void getAllEntities();

    }

    public interface Model {
        void addEntity(DataEntity dataEntity);
        Flowable<DataEntity> getEntity(int id);
        Flowable<List<DataEntity>> getAllEntities();
    }
}

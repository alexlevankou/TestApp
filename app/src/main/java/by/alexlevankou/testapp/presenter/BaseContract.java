package by.alexlevankou.testapp.presenter;

import android.arch.lifecycle.Lifecycle;

import java.util.List;

import by.alexlevankou.testapp.model.DataEntity;
import io.reactivex.Flowable;

public class BaseContract {

    public interface View {
    }

    public interface Presenter {
        void attachView(View view, Lifecycle viewLifecycle);
        void onAddClicked();

        }

    public interface Model {
        void addEntity(DataEntity dataEntity);
        Flowable<DataEntity> getEntity(int id);
        Flowable<List<DataEntity>> getAllEntities();
    }
}

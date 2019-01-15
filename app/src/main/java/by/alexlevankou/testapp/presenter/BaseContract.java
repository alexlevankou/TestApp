package by.alexlevankou.testapp.presenter;

import java.util.List;

import by.alexlevankou.testapp.model.DataEntity;
import io.reactivex.Flowable;

public class BaseContract {

    public interface View {
    }

    public interface Presenter {
        void onAddClicked();
    }

    public interface Model {
        void addEntity(DataEntity flickrPost);
        Flowable<DataEntity> getEntity(int id);
        Flowable<List<DataEntity>> getAllEntities();
    }
}

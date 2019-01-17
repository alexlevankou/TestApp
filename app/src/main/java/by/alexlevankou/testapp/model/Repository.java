package by.alexlevankou.testapp.model;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.alexlevankou.testapp.presenter.BaseContract;
import io.reactivex.Flowable;

@Singleton
public class Repository implements BaseContract.Model {

    private DataEntityDao mDataEntityDao;

    @Inject
    public Repository(DataEntityDao dataEntityDao) {
        this.mDataEntityDao = dataEntityDao;
    }

    @Override
    public Flowable<DataEntity> getEntity(int entityId) {
        return mDataEntityDao.getEntityById(entityId);
    }

    @Override
    @Nullable
    public Flowable<List<DataEntity>> getAllEntities() {
        return mDataEntityDao.getAllEntities();
    }

    @Override
    @Nullable
    public Flowable<List<DataEntity>> getSearchResults(String search) {
        return mDataEntityDao.getSearchResults(search);
    }

    @Override
    public void addEntity(DataEntity dataEntity) {
        mDataEntityDao.insert(dataEntity);
    }
}

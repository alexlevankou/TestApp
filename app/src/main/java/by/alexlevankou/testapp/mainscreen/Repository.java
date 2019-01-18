package by.alexlevankou.testapp.mainscreen;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.alexlevankou.testapp.basemvp.BaseContract;
import by.alexlevankou.testapp.database.DataEntity;
import by.alexlevankou.testapp.database.DataEntityDao;
import io.reactivex.Flowable;

@Singleton
public class Repository implements BaseContract.BaseModel {

    private DataEntityDao mDataEntityDao;

    @Inject
    public Repository(DataEntityDao dataEntityDao) {
        this.mDataEntityDao = dataEntityDao;
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

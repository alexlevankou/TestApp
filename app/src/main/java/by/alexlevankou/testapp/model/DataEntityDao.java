package by.alexlevankou.testapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DataEntityDao {

    @Nullable
    @Query("SELECT * FROM dataentity")
    Flowable<List<DataEntity>> getAllEntities();

    @Query("SELECT * FROM dataentity WHERE id = :id")
    Flowable<DataEntity> getEntityById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DataEntity dataEntity);

    @Update
    void update(DataEntity dataEntity);

    @Delete
    void delete(DataEntity dataEntity);
}

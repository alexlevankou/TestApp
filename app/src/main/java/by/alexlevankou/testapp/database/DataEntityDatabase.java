package by.alexlevankou.testapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DataEntity.class}, version = 1)
public abstract class DataEntityDatabase extends RoomDatabase {
    public abstract DataEntityDao dataEntityDao();
}

package by.alexlevankou.testapp.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import by.alexlevankou.testapp.model.DataEntityDatabase;
import by.alexlevankou.testapp.model.Repository;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    Context context;

    public RepositoryModule(Context context) {
        this.context = context;
    }

    @Provides
    @Named("ApplicationContext")
    Context getContext() {
        return context;
    }

    @Provides
    DataEntityDatabase getDatabase(){
        return Room.databaseBuilder(context, DataEntityDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    Repository getRepository(DataEntityDatabase database){
        return new Repository(database.dataEntityDao());
    }
}

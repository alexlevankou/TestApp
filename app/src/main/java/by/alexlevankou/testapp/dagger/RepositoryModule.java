package by.alexlevankou.testapp.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;

import by.alexlevankou.testapp.database.DataEntityDatabase;
import by.alexlevankou.testapp.mainscreen.Repository;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    private Context context;

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

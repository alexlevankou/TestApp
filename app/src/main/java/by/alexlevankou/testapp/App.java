package by.alexlevankou.testapp;

import android.app.Application;

import by.alexlevankou.testapp.dagger.AppComponent;
import by.alexlevankou.testapp.dagger.DaggerAppComponent;
import by.alexlevankou.testapp.dagger.RepositoryModule;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule(getApplicationContext()))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
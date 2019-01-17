package by.alexlevankou.testapp.dagger;

import by.alexlevankou.testapp.main.MainPresenter;
import dagger.Component;

@Component(modules = {RepositoryModule.class})
public interface AppComponent {
    void inject(MainPresenter presenter);
}

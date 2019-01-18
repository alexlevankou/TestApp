package by.alexlevankou.testapp.dagger;

import by.alexlevankou.testapp.mainscreen.MainPresenter;
import dagger.Component;

@Component(modules = {RepositoryModule.class})
public interface AppComponent {
    void inject(MainPresenter presenter);
}

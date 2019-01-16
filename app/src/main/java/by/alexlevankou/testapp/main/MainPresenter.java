package by.alexlevankou.testapp.main;

import by.alexlevankou.testapp.model.DataEntity;
import by.alexlevankou.testapp.presenter.BasePresenter;
import by.alexlevankou.testapp.presenter.BaseContract;

public class MainPresenter extends BasePresenter<BaseContract.View> {

    @Override
    public void addEntity() {

        DataEntity entity = new DataEntity();
        // randomize
        entity.setUserId(4);
        entity.setName("John");
        entity.setBody("Raven");
        entity.setNumber(4.45);
        // model.addEntity
    }
}

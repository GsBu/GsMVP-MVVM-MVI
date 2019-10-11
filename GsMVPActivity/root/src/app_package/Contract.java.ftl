package ${packageName};

import ${applicationPackage}.base.BaseModel;
import ${applicationPackage}.base.BasePresenter;
import ${applicationPackage}.base.BaseView;

public interface ${underscoreToCamelCase(classToResource(fragmentClass))}Contract {
    interface Model extends BaseModel {

    }

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View, Model> {

    }
}

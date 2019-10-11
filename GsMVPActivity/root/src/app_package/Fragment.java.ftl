package ${packageName};

import android.view.View;
import ${applicationPackage}.R;
import ${applicationPackage}.base.BaseFragment;

public class ${fragmentClass} extends BaseFragment<${underscoreToCamelCase(classToResource(fragmentClass))}Contract.View, ${underscoreToCamelCase(classToResource(fragmentClass))}Contract.Presenter>
        implements ${underscoreToCamelCase(classToResource(fragmentClass))}Contract.View {

    public static ${fragmentClass} newInstance() {
        return new ${fragmentClass}();
    }

	@Override
    protected int getLayoutId() {
        return R.layout.${fragmentLayout};
    }

    @Override
    protected void initViewOrEvent(View view) {
        
    }

    @Override
    protected ${underscoreToCamelCase(classToResource(fragmentClass))}Contract.Presenter createPresenter() {
        return new ${underscoreToCamelCase(classToResource(fragmentClass))}Presenter();
    }

}

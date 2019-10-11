package ${packageName};

import android.os.Bundle;
import ${applicationPackage}.R;
import ${applicationPackage}.base.BaseActivity;
import ${applicationPackage}.util.ActivityUtils;

public class ${activityClass} extends BaseActivity {

	@Override
    protected void onInitParams(Bundle bundle) {

    }

	@Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.${activityLayout});

        ${fragmentClass} fragment = (${fragmentClass}) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = ${fragmentClass}.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.container);
        }
    }
}

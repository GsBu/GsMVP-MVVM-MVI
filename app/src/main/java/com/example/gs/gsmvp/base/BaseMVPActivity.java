package com.example.gs.gsmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者    Gs
 * 时间    2020/1/15 14:49
 * 描述    Activity MVP模式封装，Google的事例都是Fragment作为View层，这样布局会多一层。
 *        所以再封装一个Activity，功能和BaseFragment一样。不想创建Fragment直接继承该类也可以成为MVP。
 */
public abstract class BaseMVPActivity<V extends BaseView, P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建继承于BasePresenter的子类的实例
     * @return
     */
    protected abstract P createPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter = null;
        }

        super.onDestroy();
    }
}

/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.gs.gsmvp.base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gs.gsmvp.util.LogUtil;

/**
 * Main UI for the statistics screen.
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment {
    protected String TAG = getClass().getSimpleName();

    protected P mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }

        View root = inflater.inflate(getLayoutId(), container, false);
        initViewOrEvent(root);

        return root;
    }

    protected abstract int getLayoutId();

    protected abstract void initViewOrEvent(View view);

    /**
     * 创建继承于BasePresenter的子类的实例
     * @return
     */
    protected abstract P createPresenter();

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        LogUtil.e(TAG, "onDestroyView");
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter = null;
        }

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtil.e(TAG, "onDestroy");
        super.onDestroy();
    }
}

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

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    private WeakReference weakReference;
    protected M mModel;

    public BasePresenter() {
        mModel = createModel();
    }

    abstract M createModel();

    /**
     * 将传入的View接口实例，通过弱引用（WeakReference）把Presenter与View进行绑定。
     *
     * @param v 界面更新接口实例
     */
    public void attach(V v) {
        weakReference = new WeakReference<>(v);
    }

    public void onResume() {

    }

    /**
     * 将Presenter与View进行解绑，并释放内存
     */
    public void detach() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }
}


package com.meticulous.wardrobe.mvp.contract;

import com.meticulous.wardrobe.base.BaseView;

/**
 * Created by c74241 on 8/8/17.
 */

public interface ExtraContract {
    interface View extends BaseView<Presenter>{
        boolean isAlive();
        void showSettingsScreen();
        void showActionScreen();
    }
    interface Presenter{
        void settingsAction();
        void aboutAction();
    }
}

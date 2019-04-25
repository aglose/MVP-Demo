package com.meticulous.wardrobe.mvp.contract;

import com.meticulous.wardrobe.base.*;

/**
 * Created by c74241 on 8/7/17.
 */

public interface WardrobeContract {
    interface View extends BaseView<Presenter>{
        boolean isAlive();
        void showCreateOutfitScreen();
        void showEditOutfitScreen();
    }

    interface Presenter{
        void createOutfitAction();
        void editOutfitAction();
    }
}

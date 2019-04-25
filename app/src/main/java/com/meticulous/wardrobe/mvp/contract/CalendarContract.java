package com.meticulous.wardrobe.mvp.contract;

import com.meticulous.wardrobe.base.BaseView;

/**
 * Created by c74241 on 8/8/17.
 */

public interface CalendarContract {
    interface View extends BaseView<Presenter> {
        boolean isAlive();
        void showOutfitDetailScreen();
        void showWeekView();
        void showMonthView();
    }
    interface Presenter{
        void outfitDetailAction();
        void weekViewAction();
        void monthViewAction();
    }
}

package com.meticulous.wardrobe.mvp.presenter;

import com.meticulous.wardrobe.data.OutfitRepository;
import com.meticulous.wardrobe.mvp.contract.CalendarContract;
import com.meticulous.wardrobe.ui.fragment.CalendarFragment;

/**
 * Created by c74241 on 8/8/17.
 */

public class CalendarPresenter implements CalendarContract.Presenter {
    private OutfitRepository outfitRepository;
    private CalendarFragment calendarFragment;

    public CalendarPresenter(OutfitRepository outfitRepository, CalendarFragment calendarFragment) {
        this.outfitRepository = outfitRepository;
        this.calendarFragment = calendarFragment;
    }


    @Override
    public void outfitDetailAction() {

    }

    @Override
    public void weekViewAction() {

    }

    @Override
    public void monthViewAction() {

    }
}

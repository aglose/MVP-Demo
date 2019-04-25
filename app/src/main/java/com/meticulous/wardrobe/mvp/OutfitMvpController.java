package com.meticulous.wardrobe.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;

import com.meticulous.wardrobe.R;
import com.meticulous.wardrobe.base.BaseActivity;
import com.meticulous.wardrobe.mvp.contract.LandingPageContract;
import com.meticulous.wardrobe.mvp.presenter.CalendarPresenter;
import com.meticulous.wardrobe.mvp.presenter.ExtraPresenter;
import com.meticulous.wardrobe.mvp.presenter.LandingPagePresenter;
import com.meticulous.wardrobe.mvp.presenter.WardrobePresenter;
import com.meticulous.wardrobe.ui.fragment.CalendarFragment;
import com.meticulous.wardrobe.ui.fragment.ExtraFragment;
import com.meticulous.wardrobe.ui.fragment.WardrobeFragment;
import com.meticulous.wardrobe.util.ActivityUtils;
import com.meticulous.wardrobe.util.Injection;

/**
 * Created by Andrew Glose on 8/8/17.
 *
 * This is the root Controller for the MVP design pattern which involves a more complex Fragment layout
 * via the bottom navigation. Here the Controller creates all the Fragments and Presenters. It designates a
 * 'parent' Presenter that will insert itself into the 3 Fragments and be responsible for the 3
 * 'child' Presenters.
 *
 * @definition Element - combination of Fragment & Presenter
 * @definition Controller - while borrowing from a different design pattern this is the root manager
 *                          of the bottom navigation Elements
 */

public class OutfitMvpController {
    private static final String TAG = "OutfitMvpController";

    private static final String WARDROBE_FRAG = "wardrobe_frag";
    private static final String CALENDAR_FRAG = "calendar_frag";
    private static final String EXTRA_FRAG = "extra_frag";

    // Calling class activity
    private final BaseActivity mBaseActivity;

    // Local Presenter that actually implements all 3 'child' Presenters
    private LandingPagePresenter mLandingPagePresenter;

    private OutfitMvpController(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }

    public static OutfitMvpController createLandingPageView(@NonNull BaseActivity baseActivity) {
        OutfitMvpController tasksMvpController = new OutfitMvpController(baseActivity);
        tasksMvpController.createRootPresenter((LandingPageContract.View) baseActivity);
        tasksMvpController.switchToWardrobeElement();
        return tasksMvpController;
    }

    private void createRootPresenter(LandingPageContract.View view) {
        // Fragments connect to their presenters through a tablet presenter:
        mLandingPagePresenter = new LandingPagePresenter(
                Injection.provideOutfitRepository(mBaseActivity),
                view);
    }

    public void switchToWardrobeElement(){
        // Fragment 1: Wardrobe
        Pair<Boolean, WardrobeFragment> mainFragment = findOrCreateWardrobeFragment(R.id.fragment_bucket);
        if(mainFragment.first){
            // already created no need to continue
            mainFragment.second.setPresenter(mLandingPagePresenter);
            return;
        }
        WardrobePresenter wardrobePresenter = createWardrobePresenter(mainFragment.second);
        mainFragment.second.setPresenter(mLandingPagePresenter);
        mLandingPagePresenter.setWardrobePresenter(wardrobePresenter);

    }
    public void switchToCalendarElement(){
        // Fragment 2: Calendar
        CalendarFragment calendarFragment = findOrCreateCalendarFragment(R.id.fragment_bucket);
        if(calendarFragment == null){
            // already created no need to continue
            return;
        }
        CalendarPresenter calendarPresenter = createCalendarPresenter(calendarFragment);
        calendarFragment.setPresenter(mLandingPagePresenter);
        mLandingPagePresenter.setCalendarPresenter(calendarPresenter);
    }

    public void switchToExtraElement(){
        //Fragment 3: Extra
        ExtraFragment extraFragment = findOrCreateExtraFragment(R.id.fragment_bucket);
        if(extraFragment == null){
            // already created no need to continue
            return;
        }
        ExtraPresenter extraPresenter = createExtraPresenter(extraFragment);
        extraFragment.setPresenter(mLandingPagePresenter);
        mLandingPagePresenter.setExtraPresenter(extraPresenter);
    }

    private WardrobePresenter createWardrobePresenter(WardrobeFragment mainFragment) {
        return new WardrobePresenter(mainFragment,
                Injection.provideOutfitRepository(mBaseActivity.getApplicationContext()));
    }

    private CalendarPresenter createCalendarPresenter(CalendarFragment calendarFragment) {
        return new CalendarPresenter(
                Injection.provideOutfitRepository(mBaseActivity.getApplicationContext()),
                calendarFragment);
    }

    private ExtraPresenter createExtraPresenter(ExtraFragment extraFragment) {
        return new ExtraPresenter(
                Injection.provideOutfitRepository(mBaseActivity.getApplicationContext()),
                extraFragment);
    }

    /*
    *  Find or create the Fragment, if needed to be created return Pair(true, frag)
    * */
    private Pair<Boolean, WardrobeFragment> findOrCreateWardrobeFragment(int fragmentBucketId) {
        WardrobeFragment wardrobeFragment = (WardrobeFragment) getFragmentByTag(WARDROBE_FRAG);

        if(wardrobeFragment == null){
            // create and attach fragment
            wardrobeFragment = WardrobeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    wardrobeFragment, fragmentBucketId, WARDROBE_FRAG);
            return new Pair<>(true, wardrobeFragment);
        }else{
            return new Pair<>(false, wardrobeFragment);
        }
    }

    /*
    *  Find or create the Fragment, if already created return null
    * */
    @Nullable private CalendarFragment findOrCreateCalendarFragment(int fragmentBucketId) {
        CalendarFragment calendarFragment = (CalendarFragment) getFragmentByTag(CALENDAR_FRAG);

        if(calendarFragment == null){
            // create and attach fragment
            calendarFragment = CalendarFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    calendarFragment, fragmentBucketId, CALENDAR_FRAG);
            return calendarFragment;
        }else{
            return null;
        }
    }

    /*
    *  Find or create the Fragment, if already created return null
    * */
    @Nullable private ExtraFragment findOrCreateExtraFragment(int fragmentBucketId) {
        ExtraFragment extraFragment = (ExtraFragment) getFragmentByTag(EXTRA_FRAG);

        if(extraFragment == null){
            // create and attach fragment
            extraFragment = ExtraFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    extraFragment, fragmentBucketId, EXTRA_FRAG);
            return extraFragment;
        }else{
            return null;
        }
    }

    private FragmentManager getSupportFragmentManager() {
        return mBaseActivity.getSupportFragmentManager();
    }

    private Fragment getFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public LandingPagePresenter getParentPresenter() {
        return mLandingPagePresenter;
    }
}

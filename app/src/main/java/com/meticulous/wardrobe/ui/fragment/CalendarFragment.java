package com.meticulous.wardrobe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meticulous.wardrobe.R;
import com.meticulous.wardrobe.base.BaseFragment;
import com.meticulous.wardrobe.mvp.contract.CalendarContract;

/**
 * Created by c74241 on 8/8/17.
 */

public class CalendarFragment extends BaseFragment implements CalendarContract.View{
    private static final String TAG = "CalendarFragment";
    private CalendarContract.Presenter mPresenter;
    private View rootView;

    public static CalendarFragment newInstance() {
        return new CalendarFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(
                R.layout.fragment_calendar, container, false);
        return rootView;
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean isAlive() {
        return isAdded();
    }

    @Override
    public void showOutfitDetailScreen() {

    }

    @Override
    public void showWeekView() {

    }

    @Override
    public void showMonthView() {

    }
}

package com.meticulous.wardrobe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meticulous.wardrobe.R;
import com.meticulous.wardrobe.base.BaseFragment;
import com.meticulous.wardrobe.mvp.contract.ExtraContract;

/**
 * Created by c74241 on 8/8/17.
 */

public class ExtraFragment extends BaseFragment implements ExtraContract.View{
    private static final String TAG = "ExtraFragment";
    private ExtraContract.Presenter mPresenter;
    private View rootView;

    public static ExtraFragment newInstance() {
        return new ExtraFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(
                R.layout.fragment_extra, container, false);
        return rootView;
    }

    @Override
    public void setPresenter(ExtraContract.Presenter presenter) {
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
    public void showSettingsScreen() {

    }

    @Override
    public void showActionScreen() {

    }
}

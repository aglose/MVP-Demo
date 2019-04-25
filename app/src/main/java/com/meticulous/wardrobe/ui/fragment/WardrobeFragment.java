package com.meticulous.wardrobe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meticulous.wardrobe.R;
import com.meticulous.wardrobe.base.BaseFragment;
import com.meticulous.wardrobe.mvp.contract.WardrobeContract;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;

/**
 * Created by c74241 on 8/8/17.
 */

public class WardrobeFragment extends BaseFragment implements WardrobeContract.View{
    private static final String TAG = "WardrobeFragment";
    private WardrobeContract.Presenter mPresenter;
    private View rootView;
    private BottomSheetBehavior<View> mBottomSheetBehavior;



    public static WardrobeFragment newInstance() {
        return new WardrobeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(
                R.layout.fragment_wardrobe, container, false);
        ButterKnife.bind(this, rootView);


        return rootView;
    }

    @OnClick(R.id.create_outfit)
    public void onClick(View view) {
        Log.d(TAG, "createOutfit: ");
        mPresenter.createOutfitAction();
    }

    @Override
    public void setPresenter(WardrobeContract.Presenter presenter) {
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
    public void showCreateOutfitScreen() {
        mBottomSheetBehavior.setState(STATE_EXPANDED);
    }

    @Override
    public void showEditOutfitScreen() {

    }
}

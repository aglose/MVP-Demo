package com.meticulous.wardrobe.mvp.presenter;

import android.support.annotation.NonNull;

import com.meticulous.wardrobe.data.OutfitDataSource;
import com.meticulous.wardrobe.mvp.contract.WardrobeContract;

/**
 * Created by c74241 on 8/7/17.
 */

public class WardrobePresenter implements WardrobeContract.Presenter {

    private OutfitDataSource outfitRepository;
    private WardrobeContract.View mView;

    public WardrobePresenter(@NonNull WardrobeContract.View mView, @NonNull OutfitDataSource outfitRepository){
        this.mView = mView;
        this.outfitRepository = outfitRepository;
    }


    @Override
    public void createOutfitAction() {
        mView.showCreateOutfitScreen();
    }

    @Override
    public void editOutfitAction() {

    }
}

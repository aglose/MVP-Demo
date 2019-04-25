package com.meticulous.wardrobe.mvp.presenter;

import com.meticulous.wardrobe.data.OutfitRepository;
import com.meticulous.wardrobe.mvp.contract.ExtraContract;
import com.meticulous.wardrobe.ui.fragment.ExtraFragment;

/**
 * Created by c74241 on 8/8/17.
 */

public class ExtraPresenter implements ExtraContract.Presenter {
    private OutfitRepository outfitRepository;
    private ExtraFragment extraFragment;

    public ExtraPresenter(OutfitRepository outfitRepository, ExtraFragment extraFragment) {
        this.outfitRepository = outfitRepository;
        this.extraFragment = extraFragment;
    }

    @Override
    public void settingsAction() {

    }

    @Override
    public void aboutAction() {

    }
}

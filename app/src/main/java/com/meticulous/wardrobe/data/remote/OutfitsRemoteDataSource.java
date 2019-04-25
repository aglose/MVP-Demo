package com.meticulous.wardrobe.data.remote;

import android.support.annotation.NonNull;

import com.meticulous.wardrobe.data.Outfit;
import com.meticulous.wardrobe.data.OutfitDataSource;

/**
 * Created by c74241 on 8/7/17.
 */

public class OutfitsRemoteDataSource implements OutfitDataSource{
    private static OutfitsRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private OutfitsRemoteDataSource() {}

    public static OutfitsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OutfitsRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void insertDefaults(Outfit[] defaultOutfits) {

    }

    @Override
    public void getOutfits(@NonNull LoadOutfitsCallback callback) {

    }

    @Override
    public void getOutfitsCustomFilter(boolean getCustom, @NonNull LoadOutfitsCallback callback) {

    }
}

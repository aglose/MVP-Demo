package com.meticulous.wardrobe.data;

import android.support.annotation.NonNull;

import com.meticulous.wardrobe.data.local.OutfitsLocalDataSource;
import com.meticulous.wardrobe.data.remote.OutfitsRemoteDataSource;

/**
 * Created by c74241 on 8/7/17.
 */

public class OutfitRepository implements OutfitDataSource {
    private static OutfitRepository INSTANCE = null;

    private final OutfitsRemoteDataSource mOutfitsRemoteDataSource;

    private final OutfitsLocalDataSource mOutfitsLocalDataSource;

    // Prevent direct instantiation.
    private OutfitRepository(@NonNull OutfitsRemoteDataSource outfitsRemoteDataSource,
                            @NonNull OutfitsLocalDataSource outfitsLocalDataSource) {
        mOutfitsRemoteDataSource = outfitsRemoteDataSource;
        mOutfitsLocalDataSource = outfitsLocalDataSource;
    }

    public static OutfitRepository getInstance(OutfitsRemoteDataSource outfitRemoteDataSource,
                                               OutfitsLocalDataSource outfitLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new OutfitRepository(outfitRemoteDataSource, outfitLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void insertDefaults(Outfit[] defaultOutfits) {
        mOutfitsLocalDataSource.insertDefaults(defaultOutfits);
    }

    @Override
    public void getOutfits(@NonNull LoadOutfitsCallback callback) {
        //currently only getting from Local
        mOutfitsLocalDataSource.getOutfits(callback);
    }

    @Override
    public void getOutfitsCustomFilter(boolean getCustom, @NonNull LoadOutfitsCallback callback) {
        mOutfitsLocalDataSource.getOutfitsCustomFilter(getCustom, callback);
    }

}

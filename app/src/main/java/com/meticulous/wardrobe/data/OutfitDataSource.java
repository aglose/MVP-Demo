package com.meticulous.wardrobe.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by c74241 on 8/7/17.
 */

public interface OutfitDataSource {
    interface LoadOutfitsCallback{
        void onOutfitsLoaded(List<Outfit> outfits);
    }

    void insertDefaults(Outfit[] defaultOutfits);

    void getOutfits(@NonNull LoadOutfitsCallback callback);

    void getOutfitsCustomFilter(boolean getCustom, @NonNull LoadOutfitsCallback callback);

}

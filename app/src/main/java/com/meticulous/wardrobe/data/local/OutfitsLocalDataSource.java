package com.meticulous.wardrobe.data.local;

import android.support.annotation.NonNull;

import com.meticulous.wardrobe.data.Outfit;
import com.meticulous.wardrobe.data.OutfitDataSource;
import com.meticulous.wardrobe.util.AppExecutors;

import java.util.List;

/**
 * Created by c74241 on 8/7/17.
 */

public class OutfitsLocalDataSource implements OutfitDataSource{
    private static volatile OutfitsLocalDataSource INSTANCE;

    private OutfitDao mOutfitsDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private OutfitsLocalDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull OutfitDao outfitsDao) {
        mAppExecutors = appExecutors;
        mOutfitsDao = outfitsDao;
    }

    public static OutfitsLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull OutfitDao outfitsDao) {
        if (INSTANCE == null) {
            synchronized (OutfitsLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OutfitsLocalDataSource(appExecutors, outfitsDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void insertDefaults(final Outfit[] defaultOutfits) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mOutfitsDao.insertAll(defaultOutfits);
            }
        });
    }

    @Override
    public void getOutfits(@NonNull final LoadOutfitsCallback callback) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Outfit> outfitList = mOutfitsDao.getOutfits();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onOutfitsLoaded(outfitList);
                    }
                });
            }
        });
    }

    @Override
    public void getOutfitsCustomFilter(final boolean getCustom, @NonNull final LoadOutfitsCallback callback) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Outfit> outfitList = mOutfitsDao.getOutfitsFilterCustom(getCustom);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onOutfitsLoaded(outfitList);
                    }
                });
            }
        });
    }
}

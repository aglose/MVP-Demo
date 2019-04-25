package com.meticulous.wardrobe.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.meticulous.wardrobe.data.OutfitRepository;
import com.meticulous.wardrobe.data.local.OutfitDatabase;
import com.meticulous.wardrobe.data.local.OutfitsLocalDataSource;
import com.meticulous.wardrobe.data.remote.OutfitsRemoteDataSource;

/**
 * Created by Andrew Glose on 8/7/17.
 *
 * Used a single point of injection for respository data
 */

public class Injection {
    public static OutfitRepository provideOutfitRepository(@NonNull Context context) {
        OutfitDatabase database = OutfitDatabase.getInstance(context);
        return OutfitRepository.getInstance(OutfitsRemoteDataSource.getInstance(),
                OutfitsLocalDataSource.getInstance(new AppExecutors(),
                        database.outfitDao()));
    }
}

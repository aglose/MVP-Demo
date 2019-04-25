package com.meticulous.wardrobe.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.meticulous.wardrobe.data.Outfit;
import com.meticulous.wardrobe.data.model.converter.Converters;

/**
 * Created by c74241 on 8/7/17.
 */

@Database(entities = {Outfit.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class OutfitDatabase extends RoomDatabase{
    private static OutfitDatabase INSTANCE;

    public abstract OutfitDao outfitDao();

    private static final Object sLock = new Object();

    public static OutfitDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        OutfitDatabase.class, "Outfits.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}

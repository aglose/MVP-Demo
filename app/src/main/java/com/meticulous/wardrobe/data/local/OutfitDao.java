package com.meticulous.wardrobe.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.meticulous.wardrobe.data.Outfit;

import java.util.List;

/**
 * Created by c74241 on 8/7/17.
 */

@Dao
public interface OutfitDao {

    @Query("SELECT * FROM outfits")
    List<Outfit> getOutfits();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Outfit outfit);

    @Insert
    void insertAll(Outfit... outfit);

    @Delete
    void delete(Outfit outfit);

    @Query("SELECT * FROM outfits WHERE hat_custom LIKE :getCustom")
    List<Outfit> getOutfitsFilterCustom(boolean getCustom);
}

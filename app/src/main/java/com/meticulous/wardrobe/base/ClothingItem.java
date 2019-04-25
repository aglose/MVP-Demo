package com.meticulous.wardrobe.base;

import android.arch.persistence.room.Ignore;

/**
 * Created by c74241 on 8/25/17.
 */

public abstract class ClothingItem {

    @Ignore
    public boolean isSelected;
}

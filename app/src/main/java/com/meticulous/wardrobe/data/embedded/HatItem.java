package com.meticulous.wardrobe.data.embedded;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.net.Uri;

import com.meticulous.wardrobe.base.ClothingItem;

/**
 * Created by c74241 on 8/19/17.
 */

public class HatItem extends ClothingItem {
    @Embedded(prefix = "hat_")
    public RGB hateColor;

    @ColumnInfo(name = "hat_size")
    public String size;

    @ColumnInfo(name = "hat_drawable")
    public int drawableId;

    @Embedded
    public Uri resourceId;

    @ColumnInfo(name = "hat_custom")
    public boolean isCustom;
}

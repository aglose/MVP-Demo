package com.meticulous.wardrobe.data.embedded;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.net.Uri;

import com.meticulous.wardrobe.base.ClothingItem;

public class PantItem extends ClothingItem{
    @Embedded(prefix = "pant_")
    public RGB pantColor;

    @ColumnInfo(name = "pant_size")
    public String size;

    @ColumnInfo(name = "pant_drawable")
    public int drawableId;

    @Embedded
    public Uri resourceId;

    @ColumnInfo(name = "pant_custom")
    public boolean isCustom;
}

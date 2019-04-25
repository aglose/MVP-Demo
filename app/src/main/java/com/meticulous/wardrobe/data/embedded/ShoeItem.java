package com.meticulous.wardrobe.data.embedded;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.net.Uri;

import com.meticulous.wardrobe.base.ClothingItem;

public class ShoeItem extends ClothingItem {
    @Embedded(prefix = "shoe_")
    public RGB shoeColor;

    @ColumnInfo(name = "shoe_size")
    public String size;

    @ColumnInfo(name = "shoe_drawable")
    public int drawableId;

    @Embedded
    public Uri resourceId;

    @ColumnInfo(name = "shoe_custom")
    public boolean isCustom;
}

package com.meticulous.wardrobe.data.embedded;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.net.Uri;

import com.meticulous.wardrobe.base.ClothingItem;

public class ShirtItem extends ClothingItem{
    @Embedded(prefix = "shirt_")
    public RGB shirtColor;

    @ColumnInfo(name = "shirt_size")
    public String size;

    @ColumnInfo(name = "shirt_drawable")
    public int drawableId;

    @Embedded
    public Uri resourceId;

    @ColumnInfo(name = "shirt_custom")
    public boolean isCustom;
}

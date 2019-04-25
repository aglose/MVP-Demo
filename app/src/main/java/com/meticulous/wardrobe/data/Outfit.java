package com.meticulous.wardrobe.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.meticulous.wardrobe.data.embedded.HatItem;
import com.meticulous.wardrobe.data.embedded.PantItem;
import com.meticulous.wardrobe.data.embedded.ShirtItem;
import com.meticulous.wardrobe.data.embedded.ShoeItem;

/**
 * Created by c74241 on 8/7/17.
 */
@Entity(tableName = "outfits")
public final class Outfit {
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @Embedded
    private HatItem hatItem;

    @Embedded
    private ShirtItem shirtItem;

    @Embedded
    private PantItem pantItem;

    @Embedded
    private ShoeItem shoeItem;

    public Outfit(){

    }

    @Ignore
    public Outfit(HatItem hat, ShirtItem shirt, PantItem pants, ShoeItem shoes){
        hatItem = hat;
        shirtItem = shirt;
        pantItem = pants;
        shoeItem = shoes;
    }


    @NonNull
    public Integer getId() {
        return mId;
    }

    public void setId(Integer id){
        mId = id;
    }
    public HatItem getHatItem() {
        return hatItem;
    }

    public void setHatItem(HatItem hatItem) {
        this.hatItem = hatItem;
    }

    public ShirtItem getShirtItem() {
        return shirtItem;
    }

    public void setShirtItem(ShirtItem shirtItem) {
        this.shirtItem = shirtItem;
    }

    public PantItem getPantItem() {
        return pantItem;
    }

    public void setPantItem(PantItem pantItem) {
        this.pantItem = pantItem;
    }

    public ShoeItem getShoeItem() {
        return shoeItem;
    }

    public void setShoeItem(ShoeItem shoeItem) {
        this.shoeItem = shoeItem;
    }
}


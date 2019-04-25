package com.meticulous.wardrobe.mvp.contract;

import android.content.Context;

import com.meticulous.wardrobe.data.OutfitDataSource;
import com.meticulous.wardrobe.data.OutfitItemType;
import com.meticulous.wardrobe.ui.adapter.HorizontalOutfitCreateAdapter;

/**
 * Created by c74241 on 8/16/17.
 */

public interface LandingPageContract {
    interface View{
        void showBottomSheet(int state);
        Context getContext();
    }

    interface Presenter{
        int getItemListSizeByType(OutfitItemType itemType);
        void loadOutfits(OutfitDataSource.LoadOutfitsCallback callback);
        void onBindRowViewAtPosition(OutfitItemType itemType, int position, HorizontalOutfitCreateAdapter.OutfitItemViewHolder holder);
        void onListItemClick(OutfitItemType itemType, int adapterPosition);
    }
}

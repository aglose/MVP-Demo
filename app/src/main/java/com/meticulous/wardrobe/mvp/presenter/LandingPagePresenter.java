package com.meticulous.wardrobe.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.meticulous.wardrobe.R;
import com.meticulous.wardrobe.WardrobeApplication;
import com.meticulous.wardrobe.base.ClothingItem;
import com.meticulous.wardrobe.data.Outfit;
import com.meticulous.wardrobe.data.OutfitDataSource;
import com.meticulous.wardrobe.data.OutfitItemType;
import com.meticulous.wardrobe.data.OutfitRepository;
import com.meticulous.wardrobe.data.embedded.HatItem;
import com.meticulous.wardrobe.data.embedded.PantItem;
import com.meticulous.wardrobe.data.embedded.ShirtItem;
import com.meticulous.wardrobe.data.embedded.ShoeItem;
import com.meticulous.wardrobe.mvp.contract.CalendarContract;
import com.meticulous.wardrobe.mvp.contract.ExtraContract;
import com.meticulous.wardrobe.mvp.contract.LandingPageContract;
import com.meticulous.wardrobe.mvp.contract.WardrobeContract;
import com.meticulous.wardrobe.ui.adapter.HorizontalOutfitCreateAdapter;
import com.meticulous.wardrobe.util.WardrobeSharedPrefs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static android.support.design.widget.BottomSheetBehavior.STATE_EXPANDED;
import static com.meticulous.wardrobe.data.OutfitItemType.HAT;
import static com.meticulous.wardrobe.data.OutfitItemType.PANTS;
import static com.meticulous.wardrobe.data.OutfitItemType.SHIRT;
import static com.meticulous.wardrobe.data.OutfitItemType.SHOES;

/**
 * Created by Andrew Glose on 8/8/17.
 *
 * This is 'parent' presenter for the Landing Page. There are 3 'children' presenters that are
 * coupled with their respective Fragment. This presenter will handle all communication from the
 * fragments. Then the respective presenter will be called which will communicate with the view.
 *
 *
 *  Example:
 *
 *  CalendarFragment communicates to -> LandingPagePresenter which handles necessary Model logic
 *  and returns responsibility to -> CalendarPresenter which then relays information
 *  back to the View -> CalendarFragment
 *
 *  View -> ParentPresenter -> Model -> ChildPresenter -> View
 */

public class LandingPagePresenter implements LandingPageContract.Presenter,
        WardrobeContract.Presenter, CalendarContract.Presenter, ExtraContract.Presenter{
    private static final String TAG = "LandingPagePresenter";

    @NonNull
    private final OutfitRepository mOutfitsRepository;
    @NonNull
    private LandingPageContract.View  mRootView;

    @Nullable
    private WardrobePresenter mWardrobePresenter;
    @Nullable
    private CalendarPresenter mCalendarPresenter;
    @Nullable
    private ExtraPresenter mExtraPresenter;

    private LinkedHashMap<OutfitItemType, ArrayList<? extends ClothingItem>> clothingItemsMap;

    public LandingPagePresenter(@NonNull OutfitRepository outfitRepository, @NonNull LandingPageContract.View rootView) {
        mOutfitsRepository = outfitRepository;
        mRootView = rootView;
        WardrobeSharedPrefs sharedPrefs = new WardrobeSharedPrefs(WardrobeApplication.getContext());
        if(sharedPrefs.isFirstLoad()){
            createDefaultOutfits();
            sharedPrefs.setFirstLoad(false);
        }
    }

    public void setWardrobePresenter(WardrobePresenter wardrobePresenter) {
        mWardrobePresenter = wardrobePresenter;
    }

    public void setCalendarPresenter(CalendarPresenter calendarPresenter) {
        mCalendarPresenter = calendarPresenter;
    }

    public void setExtraPresenter(ExtraPresenter extraPresenter){
        mExtraPresenter = extraPresenter;
    }

    @Override
    public void createOutfitAction() {
        mRootView.showBottomSheet(STATE_EXPANDED);
    }

    @Override
    public void editOutfitAction() {

    }

    @Override
    public void settingsAction() {

    }

    @Override
    public void aboutAction() {

    }

    @Override
    public void outfitDetailAction() {

    }

    @Override
    public void weekViewAction() {

    }

    @Override
    public void monthViewAction() {

    }

    @Override
    public int getItemListSizeByType(OutfitItemType itemType) {
        return clothingItemsMap.get(itemType) == null ? 0 : clothingItemsMap.get(itemType).size();
    }

    @Override
    public void onBindRowViewAtPosition(OutfitItemType itemType, int position, HorizontalOutfitCreateAdapter.OutfitItemViewHolder holder) {
        //retrieve
        ClothingItem clothingItem = clothingItemsMap.get(itemType).get(position);
        //assign image
        assignImage(itemType, clothingItem, holder);
        //show selected border
        holder.isBorderShown(clothingItem.isSelected);
    }

    /*
    *  Assign the image depending on if it is a Custom user defined image or default using the
    *  base class properties
    * */
    private void assignImage(OutfitItemType itemType, ClothingItem item, HorizontalOutfitCreateAdapter.OutfitItemViewHolder holder){
        switch (itemType){
            case HAT:
                HatItem hat = (HatItem) item;
                if(hat.isCustom){
                    holder.setImageResource(hat.resourceId);
                }else{
                    holder.setImageDrawable(hat.drawableId);
                }
                break;
            case SHIRT:
                ShirtItem shirt = (ShirtItem) item;
                if(shirt.isCustom){
                    holder.setImageResource(shirt.resourceId);
                }else{
                    holder.setImageDrawable(shirt.drawableId);
                }
                break;
            case PANTS:
                PantItem pants = (PantItem) item;
                if(pants.isCustom){
                    holder.setImageResource(pants.resourceId);
                }else{
                    holder.setImageDrawable(pants.drawableId);
                }
                break;
            case SHOES:
                ShoeItem shoes = (ShoeItem) item;
                if(shoes.isCustom){
                    holder.setImageResource(shoes.resourceId);
                }else{
                    holder.setImageDrawable(shoes.drawableId);
                }
                break;
        }
    }

    private void createClothingItemsMap(ArrayList<Outfit> outfits){
        clothingItemsMap = new LinkedHashMap<>();
        clothingItemsMap.put(HAT, new ArrayList<HatItem>());
        clothingItemsMap.put(SHIRT, new ArrayList<ShirtItem>());
        clothingItemsMap.put(PANTS, new ArrayList<PantItem>());
        clothingItemsMap.put(SHOES, new ArrayList<ShoeItem>());
        for (int i = 0; i < outfits.size(); i++) {
            Outfit outfit = outfits.get(i);

            ArrayList<HatItem> hatItems = (ArrayList<HatItem>) clothingItemsMap.get(HAT);
            hatItems.add(outfit.getHatItem());
            clothingItemsMap.put(HAT, hatItems);

            ArrayList<ShirtItem> shirtItems = (ArrayList<ShirtItem>) clothingItemsMap.get(SHIRT);
            shirtItems.add(outfit.getShirtItem());
            clothingItemsMap.put(SHIRT, shirtItems);

            ArrayList<PantItem> pantItems = (ArrayList<PantItem>) clothingItemsMap.get(PANTS);
            pantItems.add(outfit.getPantItem());
            clothingItemsMap.put(PANTS, pantItems);

            ArrayList<ShoeItem> shoeItems = (ArrayList<ShoeItem>) clothingItemsMap.get(SHOES);
            shoeItems.add(outfit.getShoeItem());
            clothingItemsMap.put(SHOES, shoeItems);
        }
    }

    private ArrayList<HatItem> retrieveHatSelectionList() {
        return new ArrayList<>();
    }

    private ArrayList<ShirtItem> retrieveShirtSelectionList() {
        return new ArrayList<>();
    }

    private ArrayList<PantItem> retrievePantSelectionList() {
        return new ArrayList<>();
    }

    private ArrayList<ShoeItem> retrieveShoeSelectionList() {
        return new ArrayList<>();
    }

    @Override
    public void onListItemClick(OutfitItemType itemType, int adapterPosition) {
        ClothingItem clothingItem = clothingItemsMap.get(itemType).get(adapterPosition);
        clothingItem.isSelected = !clothingItem.isSelected;
    }

    @Override
    public void loadOutfits(final OutfitDataSource.LoadOutfitsCallback callback) {
        // load outfits from repo into memory then callback to View to load Adapters
        mOutfitsRepository.getOutfits(new OutfitDataSource.LoadOutfitsCallback() {
            @Override
            public void onOutfitsLoaded(List<Outfit> outfits) {
                Log.d(TAG, "onOutfitsLoaded: "+outfits);
                createClothingItemsMap((ArrayList<Outfit>) outfits);
                // no need to pass list to View
                callback.onOutfitsLoaded(null);
            }
        });
    }

    private void createDefaultOutfits() {
        Outfit[] defaultOutfits = new Outfit[2];
        HatItem hatItem = new HatItem();
        hatItem.drawableId = R.drawable.ic_cake_black_24dp;
        defaultOutfits[0] = new Outfit(hatItem, new ShirtItem(), new PantItem(), new ShoeItem());
        defaultOutfits[1] = new Outfit(hatItem, new ShirtItem(), new PantItem(), new ShoeItem());

        mOutfitsRepository.insertDefaults(defaultOutfits);
    }
}

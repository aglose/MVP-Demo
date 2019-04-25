package com.meticulous.wardrobe.ui.adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meticulous.wardrobe.R;
import com.meticulous.wardrobe.data.OutfitItemType;
import com.meticulous.wardrobe.mvp.presenter.LandingPagePresenter;

/**
 * Created by Andrew Glose on 8/19/17.
 *
 * This is a MVP version of the common RecyclerView.Adapter
 *
 * The presenter will handle all logic to keep the View passive. OutfitCreateRowView interface will
 * handle the interaction between the ViewHolder and the Presenter
 *
 * @interface OutfitCreateRowView
 * @class OutfitItemViewHolder
 */
@SuppressLint("LongLogTag")
public class HorizontalOutfitCreateAdapter extends RecyclerView.Adapter<HorizontalOutfitCreateAdapter.OutfitItemViewHolder>{
    private static final String TAG = "HorizontalOutfitCreateAdapter";

    private final OutfitItemType mItemType;
    private final LandingPagePresenter mLandingPagePresenter;

    public HorizontalOutfitCreateAdapter(LandingPagePresenter landingPagePresenter, OutfitItemType itemType){
        mLandingPagePresenter = landingPagePresenter;
        mItemType = itemType;
    }

    @Override
    public OutfitItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OutfitItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_outfit_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(OutfitItemViewHolder holder, int position) {
        mLandingPagePresenter.onBindRowViewAtPosition(mItemType, position, holder);
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount mItemType: "+mItemType);
        Log.d(TAG, "getItemCount count: "+mLandingPagePresenter.getItemListSizeByType(mItemType));
        return mLandingPagePresenter.getItemListSizeByType(mItemType);
    }

    public interface OutfitCreateRowView{
        void setImageDrawable(@DrawableRes int drawableId);
        void setImageResource(Uri file);
        void isBorderShown(boolean show);
    }

    public class OutfitItemViewHolder extends RecyclerView.ViewHolder implements OutfitCreateRowView, View.OnClickListener{
        private ImageView imageView;
        private View selectedBorder;

        OutfitItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.outfit_item_image);
            selectedBorder = itemView.findViewById(R.id.image_selection_border);
        }

        @Override
        public void setImageDrawable(@DrawableRes int drawableId) {
            Log.d(TAG, "setImageDrawable: "+drawableId);
            imageView.setImageResource(drawableId);
        }

        @Override
        public void setImageResource(Uri file) {
            imageView.setImageURI(file);
        }

        @Override
        public void isBorderShown(boolean show) {
            if(show){
                selectedBorder.setVisibility(View.VISIBLE);
            }else{
                selectedBorder.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View view) {
            mLandingPagePresenter.onListItemClick(mItemType, getAdapterPosition());
            notifyItemChanged(getAdapterPosition());
        }
    }
}

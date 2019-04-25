package com.meticulous.wardrobe.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.meticulous.wardrobe.R;
import com.meticulous.wardrobe.base.BaseActivity;
import com.meticulous.wardrobe.data.Outfit;
import com.meticulous.wardrobe.data.OutfitDataSource;
import com.meticulous.wardrobe.data.OutfitItemType;
import com.meticulous.wardrobe.mvp.OutfitMvpController;
import com.meticulous.wardrobe.mvp.contract.LandingPageContract;
import com.meticulous.wardrobe.ui.adapter.HorizontalOutfitCreateAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED;

/**
 * Created by Andrew Glose on 8/7/17.
 *
 * Main activity is responsible for the bottom nav, toolbar and creating the controller
 *
 * Hierarchy: MainActivity -> OutfitMvpController -> LandingPagePresenter -> Bottom nav fragment presenters
 *                  ^                                        ^
 *                  |                                        |
 *                  +----------------------------------------+
 *                                  MVP connection
 */

public class MainActivity extends BaseActivity implements LandingPageContract.View{
    private static final String TAG = "MainActivity";

    // Bind the list views
    @BindView(R.id.hat_selection_list)
    RecyclerView hatSelectionList;
    @BindView(R.id.shirt_selection_list)
    RecyclerView shirtSelectionList;
    @BindView(R.id.pant_selection_list)
    RecyclerView pantSelectionList;
    @BindView(R.id.shoe_selection_list)
    RecyclerView shoeSelectionList;

    // Essential fields
    private OutfitMvpController mPresenterController;
    private BottomSheetBehavior<View> mBottomSheetBehavior;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Log.d(TAG, "onCreate: ");

        // Set up the toolbar.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(getString(R.string.landing_page_title));
        }

        // Create the controller
        mPresenterController = OutfitMvpController.createLandingPageView(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                chooseFragment(item);
                return false;
            }
        });

        View bottomSheet = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d(TAG, "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d(TAG, "BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d(TAG, "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d(TAG, "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d(TAG, "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d(TAG, "slideOffset: " + slideOffset);
            }
        });
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setHideable(true);

        // load the db into memory then build the adapters
        mPresenterController.getParentPresenter().loadOutfits(new OutfitDataSource.LoadOutfitsCallback() {
            @Override
            public void onOutfitsLoaded(List<Outfit> outfits) {
                buildOutfitCreationLists();
            }
        });
    }

    /*
    *  Create the horizontal lists needed for creating a new outfit
    * */
    private void buildOutfitCreationLists() {
        hatSelectionList.setHasFixedSize(true);
        HorizontalOutfitCreateAdapter adapter = new HorizontalOutfitCreateAdapter(mPresenterController.getParentPresenter(), OutfitItemType.HAT);
        hatSelectionList.setAdapter(adapter);

        shirtSelectionList.setHasFixedSize(true);
        adapter = new HorizontalOutfitCreateAdapter(mPresenterController.getParentPresenter(), OutfitItemType.SHIRT);
        shirtSelectionList.setAdapter(adapter);

        pantSelectionList.setHasFixedSize(true);
        adapter = new HorizontalOutfitCreateAdapter(mPresenterController.getParentPresenter(), OutfitItemType.PANTS);
        shirtSelectionList.setAdapter(adapter);

        shoeSelectionList.setHasFixedSize(true);
        adapter = new HorizontalOutfitCreateAdapter(mPresenterController.getParentPresenter(), OutfitItemType.SHOES);
        shoeSelectionList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @OnClick(R.id.close_bottom_sheet)
    public void onHideClick() {
        showBottomSheet(STATE_COLLAPSED);
    }

    /*
    *  Let the presenter controller take of switching fragments
    * */
    private void chooseFragment(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_wardrobe:
                mPresenterController.switchToWardrobeElement();
                break;
            case R.id.action_calendar:
                mPresenterController.switchToCalendarElement();
                break;
            case R.id.action_extra:
                mPresenterController.switchToExtraElement();
                break;
        }
    }

    @Override
    public void showBottomSheet(int newState) {
        if (newState == BottomSheetBehavior.STATE_COLLAPSED && getSupportActionBar() != null) {
            getSupportActionBar().show();

        }
        if (newState == BottomSheetBehavior.STATE_EXPANDED && getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mBottomSheetBehavior.setState(newState);
    }

    @Override
    public Context getContext() {
        return this;
    }
}

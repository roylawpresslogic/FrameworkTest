package com.example.roylaw.frameworktest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.roylaw.frameworktest.fragment.base.BaseFragment;
import com.example.roylaw.frameworktest.R;
import com.example.roylaw.frameworktest.utils.TabUtils;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavSwitchController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;
import com.ncapdevi.fragnav.tabhistory.FragNavTabHistoryController;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements FragNavController.RootFragmentListener, FragNavSwitchController, FragNavController.TransactionListener, BaseFragment.FragmentNavigation {

    // Views
    private DuoDrawerLayout mDuoDrawerLayout;
    private DuoMenuView mDuoMenuView;
    private AHBottomNavigation bottomNavigation;

    private FragNavController fragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init instagram-like back stack controller
        fragNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
                .transactionListener(this)
                .rootFragmentListener(this, TabUtils.getSize())
                .popStrategy(FragNavTabHistoryController.CURRENT_TAB)
                .switchController(this)
                .build();

        // initialize bottom nav view
        initBottomNavigation();

        // initialize drawer
        initDrawer();
    }

    @Override
    public Fragment getRootFragment(int position) {
        return TabUtils.getRootFragment(position);
    }

    @Override
    public void switchTab(int index, FragNavTransactionOptions fragNavTransactionOptions) {
        bottomNavigation.setCurrentItem(index);
    }

    @Override
    public void onTabTransaction(@Nullable Fragment fragment, int i) {
        // TransactionListener
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        // TransactionListener
    }

    @Override
    public void onBackPressed() {
        // pop fragment in current stack if reselected
        if (!fragNavController.isRootFragment()) {
            fragNavController.popFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragNavController != null) {
            fragNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (fragNavController != null) {
            fragNavController.pushFragment(fragment);
        }
    }

    private void initBottomNavigation() {
        bottomNavigation = findViewById(R.id.navigation);

        bottomNavigation.addItems(TabUtils.getNavigationItems(this));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (wasSelected) {
                    // pop fragment in current stack if reselected
                    if (!fragNavController.isRootFragment()) {
                        fragNavController.popFragment();
                    }
                } else {
                    // switch tab
                    fragNavController.switchTab(position);
                }
                return true;
            }
        });
    }

    private void initDrawer() {
        mDuoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
        mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();

        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mDuoDrawerLayout,
                null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();
    }

}

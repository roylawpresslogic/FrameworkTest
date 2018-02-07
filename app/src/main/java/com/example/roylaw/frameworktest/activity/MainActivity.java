package com.example.roylaw.frameworktest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.roylaw.frameworktest.R;
import com.example.roylaw.frameworktest.fragment.base.BaseFragment;
import com.example.roylaw.frameworktest.utils.TabUtils;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavSwitchController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;
import com.ncapdevi.fragnav.tabhistory.FragNavTabHistoryController;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends BaseActivity implements FragNavController.RootFragmentListener, FragNavSwitchController, FragNavController.TransactionListener, BaseFragment.FragmentNavigation {

    @BindView(R.id.navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.drawer)
    DuoDrawerLayout drawerLayout;
    private DuoMenuView drawerMenuView;

    private FragNavController fragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // init instagram-like back stack controller
        fragNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
                .transactionListener(this)
                .rootFragmentListener(this, TabUtils.getSize())
                .popStrategy(FragNavTabHistoryController.CURRENT_TAB)
                .switchController(this)
                .build();

        initBottomNavigation();
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

    // region Initialize View

    /**
     * initialize bottom nav view
     */
    private void initBottomNavigation() {
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

    /**
     * initialize drawer
     */
    private void initDrawer() {
        drawerMenuView = (DuoMenuView) drawerLayout.getMenuView();

        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                drawerLayout,
                null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();
    }

    // endregion Initialize View
}

package com.example.roylaw.frameworktest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.roylaw.frameworktest.fragment.base.BaseFragment;
import com.example.roylaw.frameworktest.fragment.DashboardFragment;
import com.example.roylaw.frameworktest.fragment.HomeFragment;
import com.example.roylaw.frameworktest.fragment.NotificationFragment;
import com.example.roylaw.frameworktest.R;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavSwitchController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;
import com.ncapdevi.fragnav.tabhistory.FragNavTabHistoryController;

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends AppCompatActivity implements FragNavController.RootFragmentListener, FragNavSwitchController, FragNavController.TransactionListener, BaseFragment.FragmentNavigation {

    private final int INDEX_HOME = FragNavController.TAB1;
    private final int INDEX_DASHBOARD = FragNavController.TAB2;
    private final int INDEX_NOTIFICATIONS = FragNavController.TAB3;
    private final int INDEX_TAB4 = FragNavController.TAB4;
    private final int INDEX_TAB5 = FragNavController.TAB5;

    // Views
    private DuoDrawerLayout mDuoDrawerLayout;
    private DuoMenuView mDuoMenuView;
    private AHBottomNavigation bottomNavigation;

    private FragNavController fragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container)
                .transactionListener(this)
                .rootFragmentListener(this, 5)
                .popStrategy(FragNavTabHistoryController.CURRENT_TAB)
                .switchController(this)
                .build();

        // initialize bottom nav view
        initBottomNavigation();

        // initialize drawer
        initDrawer();
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case INDEX_HOME:
                return new HomeFragment();
            case INDEX_DASHBOARD:
                return new DashboardFragment();
            case INDEX_NOTIFICATIONS:
                return new NotificationFragment();
            case INDEX_TAB4:
                return new NotificationFragment();
            case INDEX_TAB5:
                return new NotificationFragment();
            default:
                return null;
        }
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

    private void initBottomNavigation() {
        bottomNavigation = findViewById(R.id.navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_launcher_foreground, android.R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Dashboard", R.drawable.ic_launcher_foreground, android.R.color.black);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Notifications", R.drawable.ic_launcher_foreground, android.R.color.black);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Tab4", R.drawable.ic_launcher_foreground, android.R.color.black);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Tab5", R.drawable.ic_launcher_foreground, android.R.color.black);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);

        // styles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // on tab listener
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

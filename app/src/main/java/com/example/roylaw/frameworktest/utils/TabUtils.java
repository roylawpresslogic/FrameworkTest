package com.example.roylaw.frameworktest.utils;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.roylaw.frameworktest.R;
import com.example.roylaw.frameworktest.fragment.DashboardFragment;
import com.example.roylaw.frameworktest.fragment.HomeFragment;
import com.example.roylaw.frameworktest.fragment.NotificationFragment;
import com.ncapdevi.fragnav.FragNavController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roylaw on 6/2/2018.
 */

public class TabUtils {

    /**
     * Get navigation items for bottom navigation bar
     * @param context context
     * @return navigation item list
     */
    public static List<AHBottomNavigationItem> getNavigationItems(Context context) {
        ArrayList<AHBottomNavigationItem> navigationItems = new ArrayList<>();

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(context.getString(R.string.navigation_home), R.drawable.ic_launcher_foreground);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(context.getString(R.string.navigation_search), R.drawable.ic_launcher_foreground);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(context.getString(R.string.navigation_messages), R.drawable.ic_launcher_foreground);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(context.getString(R.string.navigation_notifications), R.drawable.ic_launcher_foreground);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(context.getString(R.string.navigation_profile), R.drawable.ic_launcher_foreground);

        navigationItems.add(item1);
        navigationItems.add(item2);
        navigationItems.add(item3);
        navigationItems.add(item4);
        navigationItems.add(item5);

        return navigationItems;
    }

    /**
     * Get new root fragment instance for each tab
     * @param position tab position
     * @return new root fragment instance
     */
    public static Fragment getRootFragment(int position) {
        switch (position) {
            case FragNavController.TAB1:
                return new HomeFragment();
            case FragNavController.TAB2:
                return new DashboardFragment();
            case FragNavController.TAB3:
                return new NotificationFragment();
            case FragNavController.TAB4:
                return new NotificationFragment();
            case FragNavController.TAB5:
                return new NotificationFragment();
            default:
                return null;
        }
    }

    /**
     * Get number of tabs
     * @return number of tabs
     */
    public static int getSize() {
        return 5;
    }
}

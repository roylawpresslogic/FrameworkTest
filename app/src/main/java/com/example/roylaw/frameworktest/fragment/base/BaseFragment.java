package com.example.roylaw.frameworktest.fragment.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by roylaw on 5/2/2018.
 */

public class BaseFragment extends Fragment {

    protected FragmentNavigation fragmentNavigation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentNavigation) {
            fragmentNavigation = (FragmentNavigation) context;
        }
    }

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);
    }
}

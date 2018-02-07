package com.example.roylaw.frameworktest.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by roylaw on 5/2/2018.
 */

public class BaseFragment extends Fragment {

    protected FragmentNavigation fragmentNavigation;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentNavigation) {
            fragmentNavigation = (FragmentNavigation) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // setup ButterKnife
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind ButterKnife
        unbinder.unbind();
    }

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);
    }
}

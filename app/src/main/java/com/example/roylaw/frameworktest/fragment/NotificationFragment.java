package com.example.roylaw.frameworktest.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.roylaw.frameworktest.R;
import com.example.roylaw.frameworktest.fragment.base.BaseFragment;

import java.util.Random;

/**
 * Created by roylaw on 5/2/2018.
 */

public class NotificationFragment extends BaseFragment {

    private int color;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("NotificationFragment", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("NotificationFragment", "onCreate");

        Random rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("NotificationFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_dummy, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("NotificationFragment", "onViewCreated");

        Button button = view.findViewById(R.id.button);
        button.setText("notification");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentNavigation.pushFragment(new NotificationFragment());
            }
        });

        view.setBackgroundColor(color);
    }
}

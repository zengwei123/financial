package com.cnitpm.financial.Page.Activity.Sum;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cnitpm.financial.Base.MvpFragment;
import com.cnitpm.financial.R;

import java.util.List;

public class SumFragmentAdapter extends FragmentPagerAdapter {
    private List<MvpFragment> mFragmentList;//各导航的Fragment
    public SumFragmentAdapter(FragmentManager fragmentManager, List<MvpFragment>fragments){
        super(fragmentManager);
        mFragmentList=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}

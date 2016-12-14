// # CSIT 5510     # Li Zhe        20386967    zlicx@connect.ust.hk
// # CSIT 5510     # Zhang Chen    20399782    jxzcv.zhang@connect.ust.hk
// # CSIT 5510     # Zhao Shixiong 20402060    szhaoag@connect.ust.hk
package com.cloudray.sportogether.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cloud on 2016/11/26.
 */

public class HomeActivityFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;

    public HomeActivityFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

package com.cloudray.sportogether.view.activity;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.adapter.HomeActivityFragmentPagerAdapter;
import com.cloudray.sportogether.view.fragment.EventsFragment;
import com.cloudray.sportogether.view.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Path;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,
        EventsFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener{

    private Fragment eventsFragment, meFragment;
    private List<Fragment> mFragmentList;
    private ViewPager viewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private LinearLayout eventButton, participateButton, meButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findView();
        setListener();
        init();
        setToolBar();
    }

    public void findView(){
        eventsFragment = new EventsFragment();
        meFragment = new MeFragment();

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(eventsFragment);
        mFragmentList.add(meFragment);

        viewPager = (ViewPager)findViewById(R.id.activity_home_viewpager);

        eventButton = (LinearLayout)findViewById(R.id.bottom_navigation_bar_events);
        participateButton = (LinearLayout)findViewById(R.id.bottom_navigation_bar_fast_participate);
        meButton = (LinearLayout)findViewById(R.id.bottom_navigation_bar_me);
    }

    public void setListener(){
        eventButton.setOnClickListener(this);
        participateButton.setOnClickListener(this);
        meButton.setOnClickListener(this);
    }

    public void init(){
        mFragmentPagerAdapter = new HomeActivityFragmentPagerAdapter(this.getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_navigation_bar_events:
                viewPager.setCurrentItem(0);
                break;
            case R.id.bottom_navigation_bar_fast_participate:
                break;
            case R.id.bottom_navigation_bar_me:
                viewPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    public void setToolBar(){
        Toolbar mToolbar = (Toolbar)findViewById(R.id.fragment_event_list_toolbar);
        setSupportActionBar(mToolbar);
        if(mToolbar == null)
            return;
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.fragment_event_list_collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("SporTogether");
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package com.cloudray.sportogether.view.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.adapter.HomeActivityFragmentPagerAdapter;
import com.cloudray.sportogether.tools.Blur;
import com.cloudray.sportogether.view.dialog.ConfirmPaticipateDialog;
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
                // blur effect
                LinearLayout view = (LinearLayout)findViewById(R.id.activity_home);
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Bitmap screen = Bitmap.createBitmap(view.getDrawingCache());
                view.setDrawingCacheEnabled(false);
                // scale, or it will be super slow
                screen = Bitmap.createScaledBitmap(screen, screen.getWidth()/10, screen.getHeight()/10, false);
                // do blur
                screen = Blur.fastblur(this, screen, 5);
                final ImageView imagecover = (ImageView)findViewById(R.id.activity_home_imagecover);
                imagecover.setImageBitmap(screen);
                imagecover.setVisibility(View.VISIBLE);

                // show dialog
                ConfirmPaticipateDialog dialog;
                ConfirmPaticipateDialog.Builder builder = new ConfirmPaticipateDialog(this, R.style.dialog). new Builder(this);
                dialog = builder.create();
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        imagecover.setVisibility(View.GONE);
                    }
                });
                dialog.show();

                //Toast.makeText(this, "fast participate pressed!", Toast.LENGTH_SHORT).show();
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

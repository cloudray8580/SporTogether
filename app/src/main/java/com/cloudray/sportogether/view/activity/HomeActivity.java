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
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.adapter.HomeActivityFragmentPagerAdapter;
import com.cloudray.sportogether.model.TestWechatMessage;
import com.cloudray.sportogether.network.service.TestMessageService;
import com.cloudray.sportogether.tools.Blur;
import com.cloudray.sportogether.view.dialog.ChooseDialog;
import com.cloudray.sportogether.view.dialog.ConfirmPaticipateDialog;
import com.cloudray.sportogether.view.fragment.EventsFragment;
import com.cloudray.sportogether.view.fragment.MeFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,
        EventsFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener{

    private Fragment eventsFragment, meFragment;
    private List<Fragment> mFragmentList;
    private ViewPager viewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private LinearLayout eventButton, mapButton, meButton;
    private ImageView floatButton;
    private Animation clockwiseRotate;

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
        mapButton = (LinearLayout)findViewById(R.id.bottom_navigation_bar_map);
        meButton = (LinearLayout)findViewById(R.id.bottom_navigation_bar_me);

        floatButton = (ImageView)findViewById(R.id.activity_home_float_add_button);
    }

    public void setListener(){
        eventButton.setOnClickListener(this);
        mapButton.setOnClickListener(this);
        meButton.setOnClickListener(this);
        floatButton.setOnClickListener(this);
    }

    public void init(){
        mFragmentPagerAdapter = new HomeActivityFragmentPagerAdapter(this.getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setCurrentItem(0);

        LinearInterpolator lin = new LinearInterpolator();

        clockwiseRotate = AnimationUtils.loadAnimation(this,R.anim.rotate2);
        clockwiseRotate.setInterpolator(lin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_navigation_bar_events:
                viewPager.setCurrentItem(0);
                break;
            case R.id.bottom_navigation_bar_map:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.bottom_navigation_bar_me:
                viewPager.setCurrentItem(1);
                break;
            case R.id.activity_home_float_add_button:
                /*
                Log.e("getThread--in main: ", Thread.currentThread().toString());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.104:8000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                TestMessageService service = retrofit.create(TestMessageService.class);
                Call<TestWechatMessage> call2 = service.getToken1(new TestWechatMessage("1001","lalala"));
                call2.enqueue(new Callback<TestWechatMessage>() {
                    @Override
                    public void onResponse(Call<TestWechatMessage> call, Response<TestWechatMessage> response) {
                        Toast.makeText(HomeActivity.this, response.body().errcode+" "+response.body().errmsg, Toast.LENGTH_SHORT).show();
                        Log.e("my_retrofit", response.body().errcode+"  "+response.body().errmsg);
                    }

                    @Override
                    public void onFailure(Call<TestWechatMessage> call, Throwable t) {
                        Toast.makeText(HomeActivity.this, "request failure!", Toast.LENGTH_SHORT).show();
                        Log.e("my_retrofit", t.toString());
                    }
                });
                */

                // rotate
                floatButton.startAnimation(clockwiseRotate);

                // blur effect
                RelativeLayout view = (RelativeLayout)findViewById(R.id.activity_home);
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Bitmap screen = Bitmap.createBitmap(view.getDrawingCache());
                view.setDrawingCacheEnabled(false);
                // scale, or it will be super slow
                screen = Bitmap.createScaledBitmap(screen, screen.getWidth()/20, screen.getHeight()/20, false);
                // do blur
                screen = Blur.fastblur(this, screen, 20);
                final ImageView imagecover = (ImageView)findViewById(R.id.activity_home_imagecover);
                imagecover.setImageBitmap(screen);
                imagecover.setVisibility(View.VISIBLE);

                // show dialog
                ChooseDialog dialog;
                ChooseDialog.Builder builder = new ChooseDialog(this, R.style.dialog). new Builder();
                dialog = builder.create();
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        imagecover.setVisibility(View.GONE);
                    }
                });
                dialog.show();

                //Toast.makeText(this, "fast participate pressed!", Toast.LENGTH_SHORT).show();

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

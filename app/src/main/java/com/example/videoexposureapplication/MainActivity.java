package com.example.videoexposureapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.fragments.VideoRecorderRequestFragment;
import com.example.fragments.VideoRecorderResultsFragment;
import com.example.utility.VideoFile;
import com.google.android.material.tabs.TabLayout;

import java.security.InvalidParameterException;

public class MainActivity extends AppCompatActivity implements VideoRecorderRequestFragment.OnVideoRecorderListener {

    private ViewPager mViewPager;

    private VideoRecorderRequestFragment mRequestFragment;
    private VideoRecorderResultsFragment mResultsFragment;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestFragment = new VideoRecorderRequestFragment();
        mResultsFragment = new VideoRecorderResultsFragment();

        Toolbar toolbar = (Toolbar) findViewById(com.kshirsagar.videorecorder.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onVideoRecorded(VideoFile videoFile) {
        mResultsFragment.addVideoFile(videoFile);
        mViewPager.setCurrentItem(1);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mRequestFragment;
                case 1:
                    return mResultsFragment;
                default:
                    throw new InvalidParameterException("Invalid position");
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.recording);
                case 1:
                    return getString(R.string.results);
                default:
                    throw new InvalidParameterException("Invalid position");
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}

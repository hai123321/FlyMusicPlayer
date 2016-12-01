package com.example.windykiss.homework15_0102.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windykiss.homework15_0102.R;
import com.example.windykiss.homework15_0102.adapters.PagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout tab_layout;

    @BindView(R.id.pager)
    ViewPager viewPager;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);
        setupUI();
        return v;
    }


    private void setupUI() {
        toolbar.setTitle("Windy Music Player");
        toolbar.inflateMenu(R.menu.actionbar_menu);

        tab_layout.addTab(tab_layout.newTab().setText("Genres"));
        tab_layout.addTab(tab_layout.newTab().setText("Playlist"));
        tab_layout.addTab(tab_layout.newTab().setText("Offline"));
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapter adapter = new PagerAdapter(this.getChildFragmentManager(), tab_layout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

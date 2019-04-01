package com.benlefevre.mynews.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.adapters.PageAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_tablayout)
    TabLayout mActivityMainTablayout;
    @BindView(R.id.activity_main_viewpager)
    ViewPager mActivityMainViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configureToolBar();
        configureViewPager();
    }

    /**
     * Configures the MainActivity's ViewPager with the PageAdapter that defines the number of pages
     * displayed and their titles in the TabLayout
     */
    private void configureViewPager() {
        mActivityMainViewpager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        mActivityMainTablayout.setupWithViewPager(mActivityMainViewpager);
        mActivityMainTablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.activity_main_search_menu:
//                Opens Search Activity when user clicks on the search icon
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.notification_menu:
//                Opens Notification Activity when user clicks in the notification text in menu
                intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Configures toolbar as ActionBar
     */
    private void configureToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}

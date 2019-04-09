package com.benlefevre.mynews.controllers.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;

import com.benlefevre.mynews.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.benlefevre.mynews.utils.Constants.ID;
import static com.benlefevre.mynews.utils.Constants.PREFERENCES;
import static com.benlefevre.mynews.utils.Constants.URL;

public class DisplayArticleActivity extends AppCompatActivity {

    private WebView mWebview;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_article);
        mSharedPreferences = getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        configureToolbar();
        mWebview = findViewById(R.id.webview);
//        We load the WebView with the intent's extra named URL
        mWebview.loadUrl(getIntent().getStringExtra(URL));
//        We put in SharedPreferences a short Title as an ID to know if the user has already read an article
        mSharedPreferences.edit().putString(getIntent().getStringExtra(ID),"id").apply();
    }

    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.webviewToolbarTitle));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
}

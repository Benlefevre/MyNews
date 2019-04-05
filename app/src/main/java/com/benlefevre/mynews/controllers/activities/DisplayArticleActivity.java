package com.benlefevre.mynews.controllers.activities;

import android.os.Bundle;
import android.webkit.WebView;

import com.benlefevre.mynews.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.benlefevre.mynews.utils.Constants.URL;

public class DisplayArticleActivity extends AppCompatActivity {

    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_article);
        configureToolbar();
        mWebview = findViewById(R.id.webview);
        mWebview.loadUrl(getIntent().getStringExtra(URL));
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

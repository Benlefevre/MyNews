package com.benlefevre.mynews.controllers.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.benlefevre.mynews.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.query_term)
    EditText mQueryTerm;
    @BindView(R.id.begin_date)
    TextInputEditText mBeginDate;
    @BindView(R.id.end_date)
    TextInputEditText mEndDate;
    @BindView(R.id.checkbox_arts)
    MaterialCheckBox mCheckboxArts;
    @BindView(R.id.checkbox_business)
    MaterialCheckBox mCheckboxBusiness;
    @BindView(R.id.checkbox_entrepreneurs)
    MaterialCheckBox mCheckboxEntrepreneurs;
    @BindView(R.id.checkbox_politics)
    MaterialCheckBox mCheckboxPolitics;
    @BindView(R.id.checkbox_sport)
    MaterialCheckBox mCheckboxSport;
    @BindView(R.id.checkbox_travel)
    MaterialCheckBox mCheckboxTravel;
    @BindView(R.id.switch_notification)
    SwitchCompat mSwitchNotification;
    @BindView(R.id.search_query_button)
    MaterialButton mSearchQueryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        configureToolbar();
        mSwitchNotification.setVisibility(View.GONE);

    }

    /**
     * Configures toolbar as ActionBar and allows to display Up item
     */
    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.searchToolbarTitle));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
}

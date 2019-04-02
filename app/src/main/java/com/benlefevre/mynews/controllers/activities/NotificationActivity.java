package com.benlefevre.mynews.controllers.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.benlefevre.mynews.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.query_term)
    EditText mQueryTerm;
    @BindView(R.id.begin_date)
    TextInputEditText mBeginDate;
    @BindView(R.id.end_date)
    TextInputEditText mEndDate;
    @BindView(R.id.checkbox_arts)
    AppCompatCheckBox mCheckboxArts;
    @BindView(R.id.checkbox_business)
    AppCompatCheckBox mCheckboxBusiness;
    @BindView(R.id.checkbox_entrepreneurs)
    AppCompatCheckBox mCheckboxEntrepreneurs;
    @BindView(R.id.checkbox_politics)
    AppCompatCheckBox mCheckboxPolitics;
    @BindView(R.id.checkbox_sport)
    AppCompatCheckBox mCheckboxSport;
    @BindView(R.id.checkbox_travel)
    AppCompatCheckBox mCheckboxTravel;
    @BindView(R.id.switch_notification)
    SwitchCompat mSwitchNotification;
    @BindView(R.id.search_query_button)
    MaterialButton mSearchQueryButton;
    @BindView(R.id.dates)
    LinearLayout mDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        configureToolbar();
        configureLayout();
    }

    /**
     * SearchActivity and NotificationActivity use a common layout.
     * Disables views that are no needed in this activity
     */
    private void configureLayout() {
        mSearchQueryButton.setVisibility(View.GONE);
        mBeginDate.setVisibility(View.GONE);
        mEndDate.setVisibility(View.GONE);
        mDates.setVisibility(View.GONE);
    }

    /**
     * Configures toolbar as ActionBar and allows to display Up item
     */
    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.notificationToolBarTitle));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
}

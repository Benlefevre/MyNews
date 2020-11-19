package com.benlefevre.mynews.controllers.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.benlefevre.mynews.R;
import com.benlefevre.mynews.utils.Constants;
import com.benlefevre.mynews.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    private String mQuery;
    private String mFilterQueries;
    private String mBeginQuery;
    private String mEndQuery;
    private int mNbChecked;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mUnbinder = ButterKnife.bind(this);
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


    @OnClick({R.id.begin_date, R.id.end_date, R.id.search_query_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.begin_date:
//                Displays a Date Picker when user click and updates mBeginDate when user choose a date
                displayDatePicker(0);
                break;
            case R.id.end_date:
//                Displays a Date Picker when user click and updates mEndDate when user choose a date
                displayDatePicker(1);
                break;
            case R.id.search_query_button:
//                Displays toast if needed queries missing and sends an intent if all queries are filled
                configureQueries();
                if (mQuery.isEmpty())
                    Toast.makeText(this, getString(R.string.query_term_empty), Toast.LENGTH_SHORT).show();
                else if (mNbChecked == 0)
                    Toast.makeText(this, getString(R.string.checkbox_selected), Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(this,SearchResultActivity.class);
                    intent.putExtra(Constants.QUERY,mQuery);
                    intent.putExtra(Constants.FILTERQUERY,mFilterQueries);
                    intent.putExtra(Constants.BEGINDATE,mBeginQuery);
                    intent.putExtra(Constants.ENDDATE,mEndQuery);
                    startActivity(intent);
                }
        }
    }

    /**
     * Creates a DatePickerDialog with different Theme according to the build version. When user set a date in the DatePicker,
     * the corresponding EditText is updated.
     *
     * @param tag A value to set the text in the correct EditText
     */
    private void displayDatePicker(int tag) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int style;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            style = R.style.Theme_AppCompat_DayNight_Dialog;
        else
            style = R.style.MyNewsDatePicker;
        DatePickerDialog dialog = new DatePickerDialog(this, style, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                switch (tag) {
                    case 0:
                        mBeginDate.setText(Utils.convertDateForDisplay(date));
                        break;
                    case 1:
                        mEndDate.setText(Utils.convertDateForDisplay(date));
                        break;
                }
            }
        }, year, month, day);
        dialog.show();
    }

    /**
     * Configures all needed queries to the http request to NyTimes Search API
     */
    private void configureQueries() {
        mNbChecked = 0;
        mQuery = mQueryTerm.getText().toString();
        mBeginQuery = mBeginDate.getText().toString();
        mEndQuery = mEndDate.getText().toString();
        verifyIfOneCheckboxIsChecked();
        mFilterQueries = Utils.configureFilterQueries(mCheckboxArts, mCheckboxBusiness, mCheckboxPolitics, mCheckboxSport, mCheckboxEntrepreneurs, mCheckboxTravel);
    }

    /**
     * Verifies if one CheckBox is checked before send an intent.
     */
    private void verifyIfOneCheckboxIsChecked() {
        if (mCheckboxArts.isChecked())
            mNbChecked++;
        if (mCheckboxBusiness.isChecked())
            mNbChecked++;
        if (mCheckboxPolitics.isChecked())
            mNbChecked++;
        if (mCheckboxEntrepreneurs.isChecked())
            mNbChecked++;
        if (mCheckboxSport.isChecked())
            mNbChecked++;
        if (mCheckboxTravel.isChecked())
            mNbChecked++;
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}

package com.benlefevre.mynews.controllers.activities;

import com.benlefevre.mynews.R;
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;

@RunWith(AndroidJUnit4.class)
public class NotificationActivitytest {

    @Rule
    public ActivityScenarioRule<NotificationActivity> mNotificationActivityActivityScenarioRule = new ActivityScenarioRule<NotificationActivity>(NotificationActivity.class);
    @Rule
    public ClearPreferencesRule mClearPreferencesRule = new ClearPreferencesRule();

    @Test
    public void NotificationActivityTest(){
        assertDisplayed(R.id.toolbar);
        assertDisplayed(R.string.notificationToolBarTitle);
        assertNotExist(R.id.activity_main_search_menu);
        assertNotExist(R.id.notification_menu);
        assertNotExist(R.id.about_menu);
        assertNotExist(R.id.help_menu);
        assertNotDisplayed(R.id.dates);
        assertNotDisplayed(R.id.end_date);
        assertNotDisplayed(R.id.begin_date);
        assertNotDisplayed(R.id.search_query_button);
        assertDisplayed(R.id.query_term);
        assertHint(R.id.query_term,R.string.search_query_term_hint);
        assertDisplayed(R.id.checkbox_arts);
        assertDisplayed(R.id.checkbox_sport);
        assertDisplayed(R.id.checkbox_politics);
        assertDisplayed(R.id.checkbox_travel);
        assertDisplayed(R.id.checkbox_entrepreneurs);
        assertDisplayed(R.id.checkbox_business);
        assertDisplayed(R.id.switch_notification);
    }
}

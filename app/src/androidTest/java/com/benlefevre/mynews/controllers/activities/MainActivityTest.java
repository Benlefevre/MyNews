package com.benlefevre.mynews.controllers.activities;

import com.benlefevre.mynews.R;
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    @Rule
    public ClearPreferencesRule mClearPreferencesRule = new ClearPreferencesRule();

    @Test
    public void mainActivityTest(){
        assertDisplayed(R.id.toolbar);
        assertDisplayed(R.string.app_name);
        assertDisplayed(R.id.activity_main_search_menu);
        assertNotExist(R.id.notification_menu);
        assertNotExist(R.id.about_menu);
        assertNotExist(R.id.help_menu);
        clickMenu(R.id.activity_main_search_menu);
        assertDisplayed(R.id.toolbar);
        assertDisplayed(R.string.searchToolbarTitle);
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        assertDisplayed(R.string.app_name);
        clickMenu(R.id.notification_menu);
        assertDisplayed(R.id.toolbar);
        assertDisplayed(R.string.notificationToolBarTitle);
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        assertDisplayed(R.string.app_name);
        assertDisplayed(R.id.activity_main_tablayout);
        assertDisplayed(R.id.activity_main_viewpager);
        assertDisplayed(R.string.topstories);
        assertDisplayed(R.string.mostpopular);
        assertDisplayed(R.string.automobiles);

    }
}
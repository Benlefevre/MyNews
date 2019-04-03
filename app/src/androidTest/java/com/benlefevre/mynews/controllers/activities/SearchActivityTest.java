package com.benlefevre.mynews.controllers.activities;

import android.view.View;

import com.benlefevre.mynews.R;
import com.schibsted.spain.barista.interaction.BaristaSwipeRefreshInteractions;
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked;
import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertCustomAssertionAtPosition;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListNotEmpty;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;
import static com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard;
import static com.schibsted.spain.barista.interaction.BaristaPickerInteractions.setDateOnPicker;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public ActivityScenarioRule<SearchActivity> mSearchActivityActivityScenarioRule = new ActivityScenarioRule<>(SearchActivity.class);
    @Rule
    public ClearPreferencesRule mClearPreferencesRule = new ClearPreferencesRule();
    private View mDecorView;

    @Before
    public void setUp(){
        mSearchActivityActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<SearchActivity>() {
            @Override
            public void perform(SearchActivity activity) {
                mDecorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void searchActivityTest(){
        assertDisplayed(R.id.toolbar);
        assertDisplayed(R.string.searchToolbarTitle);
        assertNotExist(R.id.activity_main_search_menu);
        assertNotExist(R.id.notification_menu);
        assertNotExist(R.id.about_menu);
        assertNotExist(R.id.help_menu);
        assertNotDisplayed(R.id.switch_notification);
        assertDisplayed(R.id.query_term);
        assertHint(R.id.query_term,R.string.search_query_term_hint);
        assertDisplayed(R.id.begin_date);
        assertHint(R.id.begin_date,R.string.begin_date_hint);
        assertDisplayed(R.id.end_date);
        assertHint(R.id.end_date,R.string.end_date_hint);
        assertDisplayed(R.id.search_query_button);
        assertDisplayed(R.id.checkbox_arts);
        assertDisplayed(R.id.checkbox_sport);
        assertDisplayed(R.id.checkbox_politics);
        assertDisplayed(R.id.checkbox_travel);
        assertDisplayed(R.id.checkbox_entrepreneurs);
        assertDisplayed(R.id.checkbox_business);
        clickOn(R.id.search_query_button);
        onView(withText(R.string.query_term_empty)).inRoot(withDecorView(not(mDecorView))).check(matches(isDisplayed()));
        sleep(1500);
        writeTo(R.id.query_term,"nba");
        assertContains(R.id.query_term,"nba");
        closeKeyboard();
        clickOn(R.id.begin_date);
        setDateOnPicker(2018,12,01);
        assertContains(R.id.begin_date,"01/12/2018");
        clickOn(R.id.end_date);
        setDateOnPicker(2019,03,15);
        assertContains(R.id.end_date,"15/03/2019");
        clickOn(R.id.search_query_button);
        onView(withText(R.string.checkbox_selected)).inRoot(withDecorView(not(mDecorView))).check(matches(isDisplayed()));
        clickOn(R.id.checkbox_sport);
        assertChecked(R.id.checkbox_sport);
        clickOn(R.id.search_query_button);
        assertDisplayed(R.id.toolbar);
        assertDisplayed(R.string.result_search_toolbar_title);
        assertNotExist(R.id.activity_main_search_menu);
        assertNotExist(R.id.notification_menu);
        assertNotExist(R.id.about_menu);
        assertNotExist(R.id.help_menu);
        assertDisplayed(R.id.search_result_swipe_layout);
        BaristaSwipeRefreshInteractions.refresh();
        assertDisplayed(R.id.search_result_recycler_view);
        assertListNotEmpty(R.id.search_result_recycler_view);







    }

}
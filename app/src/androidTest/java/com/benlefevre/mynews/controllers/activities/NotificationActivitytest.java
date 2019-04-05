package com.benlefevre.mynews.controllers.activities;

import android.view.View;

import com.benlefevre.mynews.R;
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertUnchecked;
import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;
import static com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard;
import static com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class NotificationActivitytest {

    private View mDecorView;

    @Rule
    public ActivityScenarioRule<NotificationActivity> mNotificationActivityActivityScenarioRule = new ActivityScenarioRule<NotificationActivity>(NotificationActivity.class);
    @Rule
    public ClearPreferencesRule mClearPreferencesRule = new ClearPreferencesRule();

    @Before
    public void setUp(){
        mNotificationActivityActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<NotificationActivity>() {
            @Override
            public void perform(NotificationActivity activity) {
                mDecorView = activity.getWindow().getDecorView();
            }
        });
    }

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
        clickOn(R.id.switch_notification);
        onView(withText(R.string.query_term_empty)).inRoot(withDecorView(not(mDecorView))).check(matches(isDisplayed()));
        assertUnchecked(R.id.switch_notification);
        sleep(2500);
        writeTo(R.id.query_term,"nba");
        closeKeyboard();
        clickOn(R.id.switch_notification);
        onView(withText(R.string.checkbox_selected)).inRoot(withDecorView(not(mDecorView))).check(matches(isDisplayed()));
        assertUnchecked(R.id.switch_notification);
        clickOn(R.id.checkbox_sport);
        clickOn(R.id.switch_notification);
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        assertDisplayed(R.string.app_name);
        clickMenu(R.id.notification_menu);
        assertContains(R.id.query_term,"nba");
        assertChecked(R.id.checkbox_sport);
        assertChecked(R.id.switch_notification);
        clickOn(R.id.switch_notification);
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        clickMenu(R.id.notification_menu);
        assertHint(R.id.query_term, R.string.search_query_term_hint);
        assertUnchecked(R.id.checkbox_sport);
        assertUnchecked(R.id.switch_notification);
    }
}

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
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListNotEmpty;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition;
import static com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    @Rule
    public ClearPreferencesRule mClearPreferencesRule = new ClearPreferencesRule();

    @Test
    public void toolbarMainActivityTest(){
        assertDisplayed(R.id.toolbar);
        assertDisplayed(R.string.app_name);
        assertDisplayed(R.id.activity_main_search_menu);
        assertNotExist(R.id.notification_menu);
        assertNotExist(R.id.about_menu);
        assertNotExist(R.id.help_menu);
    }

    @Test
    public void menuMainActivityTest(){
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
    }

    @Test
    public void viewPagerAndTablayoutMainActivityTest(){
        assertDisplayed(R.id.activity_main_tablayout);
        assertDisplayed(R.id.activity_main_viewpager);
        assertDisplayed(R.string.topstories);
        assertDisplayed(R.string.mostpopular);
        assertDisplayed(R.string.automobiles);
        onView(allOf(withText(R.string.topstories), isDescendantOfA(withId(R.id.activity_main_tablayout)))).perform(click()).check(matches(isSelected()));
        swipeLeft();
        onView(allOf(withText(R.string.mostpopular), isDescendantOfA(withId(R.id.activity_main_tablayout)))).perform(click()).check(matches(isSelected()));
        swipeLeft();
        onView(allOf(withText(R.string.automobiles), isDescendantOfA(withId(R.id.activity_main_tablayout)))).perform(click()).check(matches(isSelected()));
    }

    @Test
    public void mainActivityTest() {
        sleep(1500);
        assertDisplayed(R.id.article_fragment_recycler_view);
        swipeLeft();
        assertDisplayed(R.id.article_fragment_recycler_view);
        swipeLeft();
        assertDisplayed(R.id.article_fragment_recycler_view);
        assertListNotEmpty(R.id.article_fragment_recycler_view);
        scrollListToPosition(R.id.article_fragment_recycler_view, 5);
        clickOn(R.string.mostpopular);
        assertDisplayed(R.id.article_fragment_recycler_view);
        assertListNotEmpty(R.id.article_fragment_recycler_view);
        scrollListToPosition(R.id.article_fragment_recycler_view, 15);
        clickOn(R.string.topstories);
        assertDisplayed(R.id.article_fragment_recycler_view);
        assertListNotEmpty(R.id.article_fragment_recycler_view);
        scrollListToPosition(R.id.article_fragment_recycler_view, 10);


        // WebView tests
        clickListItem(R.id.article_fragment_recycler_view, 0);
        assertDisplayed(R.string.webviewToolbarTitle);
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
        assertDisplayed(R.string.app_name);
    }
}
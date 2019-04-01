package com.benlefevre.mynews.adapters;

import com.benlefevre.mynews.controllers.fragments.ArticleFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    /**
     * Returns a new ArticleFragment that displays the correct items in a RecyclerView according to
     * the page's position in the MainActivity's ViewPager
     * @param position Page's position in MainActivity's ViewPager
     * @return ArticleFragment
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ArticleFragment.newInstance(0);
            case 1:
                return ArticleFragment.newInstance(1);
            case 2:
                return ArticleFragment.newInstance(2);
            default:
                return null;
        }
    }

    /**
     * Returns the number of pages in the ViewPager
     */
    @Override
    public int getCount() {
        return 3;
    }

    /**
     * Returns the string that is displayed in the TabLayout according to the position in the ViewPager
     * @param position the position in the ViewPager
     * @return strings displayed in Tab
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top Stories";
            case 1:
                return "Most Popular";
            case 2:
                return "Automobiles";
            default:
                return null;
        }
    }
}

package br.com.localhost8080.findyourbus.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.localhost8080.findyourbus.dto.BusDepartureCalendarEnum;

public class DepartureListFragmentPagerAdapter extends FragmentPagerAdapter {

    private final String[] tabTitles = BusDepartureCalendarEnum.stringValues();
    private Context context;

    public DepartureListFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return DepartureListFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
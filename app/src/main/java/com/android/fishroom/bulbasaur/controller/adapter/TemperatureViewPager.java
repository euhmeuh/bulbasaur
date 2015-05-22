package com.android.fishroom.bulbasaur.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.fishroom.bulbasaur.fragments.temperature.TDailyFragment;
import com.android.fishroom.bulbasaur.fragments.temperature.TMonthlyFragment;
import com.android.fishroom.bulbasaur.fragments.temperature.TWeeklyFragment;

public class TemperatureViewPager extends ViewPagerAdapter
{
	public TemperatureViewPager(FragmentManager fragmentManager)
	{
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int position)
	{
		switch (position)
		{
			case 0:
				return TDailyFragment.newInstance();
			case 1:
				return TWeeklyFragment.newInstance();
			case 2:
				return TMonthlyFragment.newInstance();
			default:
				return null;
		}
	}
}

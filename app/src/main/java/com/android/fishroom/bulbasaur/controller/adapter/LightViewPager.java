package com.android.fishroom.bulbasaur.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.fishroom.bulbasaur.fragments.light.LDailyFragment;
import com.android.fishroom.bulbasaur.fragments.light.LMonthlyFragment;
import com.android.fishroom.bulbasaur.fragments.light.LWeeklyFragment;

public class LightViewPager extends ViewPagerAdapter
{
	public LightViewPager(FragmentManager fragmentManager)
	{
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int position)
	{
		switch (position)
		{
			case 0:
				return LDailyFragment.newInstance();
			case 1:
				return LWeeklyFragment.newInstance();
			case 2:
				return LMonthlyFragment.newInstance();
			default:
				return null;
		}
	}
}
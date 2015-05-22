package com.android.fishroom.bulbasaur.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.fishroom.bulbasaur.fragments.humidity.HDailyFragment;
import com.android.fishroom.bulbasaur.fragments.humidity.HMonthlyFragment;
import com.android.fishroom.bulbasaur.fragments.humidity.HWeeklyFragment;

public class HumidityViewPager extends ViewPagerAdapter
{
	public HumidityViewPager(FragmentManager fragmentManager)
	{
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int position)
	{
		switch (position)
		{
			case 0:
				return HDailyFragment.newInstance();
			case 1:
				return HWeeklyFragment.newInstance();
			case 2:
				return HMonthlyFragment.newInstance();
			default:
				return null;
		}
	}
}
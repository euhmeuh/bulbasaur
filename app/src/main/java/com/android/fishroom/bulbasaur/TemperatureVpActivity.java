package com.android.fishroom.bulbasaur;

import android.os.Bundle;

import com.android.fishroom.bulbasaur.lib.util.ViewPagerActivity;
import com.android.fishroom.bulbasaur.controller.adapter.TemperatureViewPager;

public class TemperatureVpActivity extends ViewPagerActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		adapterViewPager = new TemperatureViewPager(getSupportFragmentManager());

		toolbar.setTitle(getResources().getString(R.string.temperature));
		viewPager.setAdapter(adapterViewPager);
		slidingTabLayout.setViewPager(viewPager);

	}
}

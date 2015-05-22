package com.android.fishroom.bulbasaur;

import android.os.Bundle;

import com.android.fishroom.bulbasaur.Model.ViewPagerActivity;
import com.android.fishroom.bulbasaur.controller.adapter.TemperatureViewPager;

public class TemperatureVpActivity extends ViewPagerActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		adapterViewPager = new TemperatureViewPager(getSupportFragmentManager());
		viewPager.setAdapter(adapterViewPager);
		slidingTabLayout.setViewPager(viewPager);
	}
}

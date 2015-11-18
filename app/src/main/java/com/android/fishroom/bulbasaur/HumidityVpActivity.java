package com.android.fishroom.bulbasaur;

import android.os.Bundle;

import com.android.fishroom.bulbasaur.lib.util.ViewPagerActivity;
import com.android.fishroom.bulbasaur.controller.adapter.HumidityViewPager;

public class HumidityVpActivity extends ViewPagerActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		toolbar.setTitle(getResources().getString(R.string.humidity));
		adapterViewPager = new HumidityViewPager(getSupportFragmentManager());
		viewPager.setAdapter(adapterViewPager);
		slidingTabLayout.setViewPager(viewPager);
	}
}
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

		super.adapterViewPager = new TemperatureViewPager(getSupportFragmentManager());
		super.viewPager.setAdapter(adapterViewPager);
	}
}

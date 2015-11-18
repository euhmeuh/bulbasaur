package com.android.fishroom.bulbasaur;

import android.os.Bundle;

import com.android.fishroom.bulbasaur.lib.util.ViewPagerActivity;
import com.android.fishroom.bulbasaur.controller.adapter.LightViewPager;

public class LightVpActivity extends ViewPagerActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		toolbar.setTitle(getResources().getString(R.string.light));
		adapterViewPager = new LightViewPager(getSupportFragmentManager());
		viewPager.setAdapter(adapterViewPager);
		slidingTabLayout.setViewPager(viewPager);
	}
}

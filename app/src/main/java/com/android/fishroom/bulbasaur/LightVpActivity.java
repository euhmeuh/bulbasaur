package com.android.fishroom.bulbasaur;

import android.os.Bundle;

import com.android.fishroom.bulbasaur.Model.ViewPagerActivity;
import com.android.fishroom.bulbasaur.adapter.LightViewPager;

public class LightVpActivity extends ViewPagerActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		super.adapterViewPager = new LightViewPager(getSupportFragmentManager());
		super.viewPager.setAdapter(adapterViewPager);
	}
}

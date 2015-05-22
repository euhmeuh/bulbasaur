package com.android.fishroom.bulbasaur.Model;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.adapter.ViewPagerAdapter;

public class ViewPagerActivity extends FragmentActivity
{
	protected ViewPagerAdapter adapterViewPager;
	protected ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager);

		viewPager = (ViewPager) findViewById(R.id.view_pager);
	}
}

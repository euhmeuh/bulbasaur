package com.android.fishroom.bulbasaur.fragments.temperature;

import com.android.fishroom.bulbasaur.lib.util.dailyFragment;

public class TDailyFragment extends dailyFragment
{
	public static TDailyFragment newInstance()
	{
		TDailyFragment fragment = new TDailyFragment();

		return fragment;
	}

	public TDailyFragment()
	{
	}

	@Override
	protected void setCategorie()
	{
		categorie = 0;
	}
}

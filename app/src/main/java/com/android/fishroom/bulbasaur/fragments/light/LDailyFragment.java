package com.android.fishroom.bulbasaur.fragments.light;

import com.android.fishroom.bulbasaur.lib.util.dailyFragment;

public class LDailyFragment extends dailyFragment
{
	public static LDailyFragment newInstance()
	{
		LDailyFragment fragment = new LDailyFragment();

		return fragment;
	}

	public LDailyFragment()
	{
	}

	@Override
	protected void setCategorie()
	{
		categorie = 1;
	}
}
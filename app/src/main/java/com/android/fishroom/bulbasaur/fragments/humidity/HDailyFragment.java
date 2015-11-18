package com.android.fishroom.bulbasaur.fragments.humidity;

import com.android.fishroom.bulbasaur.lib.util.dailyFragment;

public class HDailyFragment extends dailyFragment
{
	public static HDailyFragment newInstance()
	{
		HDailyFragment fragment = new HDailyFragment();

		return fragment;
	}

	public HDailyFragment()
	{
	}

	@Override
	protected void setCategorie()
	{
		categorie = 2;
	}
}
package com.android.fishroom.bulbasaur.fragments.temperature;

import com.android.fishroom.bulbasaur.lib.util.weeklyFragment;

public class TWeeklyFragment extends weeklyFragment
{
	public static TWeeklyFragment newInstance()
	{
		TWeeklyFragment fragment = new TWeeklyFragment();

		return fragment;
	}

	public TWeeklyFragment()
	{
	}

	@Override
	protected void setCategorie()
	{
		categorie = 0;
	}
}
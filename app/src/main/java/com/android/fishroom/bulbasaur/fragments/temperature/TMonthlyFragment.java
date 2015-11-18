package com.android.fishroom.bulbasaur.fragments.temperature;

import com.android.fishroom.bulbasaur.lib.util.MonthlyFragment;

public class TMonthlyFragment extends MonthlyFragment
{
	public static TMonthlyFragment newInstance()
	{
		TMonthlyFragment fragment = new TMonthlyFragment();

		return fragment;
	}

	public TMonthlyFragment()
	{
	}

	@Override
	protected void setCategorie()
	{
		categorie = 0;
	}
}
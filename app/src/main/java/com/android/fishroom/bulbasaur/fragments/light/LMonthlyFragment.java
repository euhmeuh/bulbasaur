package com.android.fishroom.bulbasaur.fragments.light;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.lib.util.MonthlyFragment;

public class LMonthlyFragment extends MonthlyFragment
{
	public static LMonthlyFragment newInstance()
	{
		LMonthlyFragment fragment = new LMonthlyFragment();

		return fragment;
	}

	public LMonthlyFragment()
	{
	}
	@Override
	protected void setCategorie()
	{
		categorie = 1;
	}
}
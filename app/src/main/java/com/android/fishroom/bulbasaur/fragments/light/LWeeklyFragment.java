package com.android.fishroom.bulbasaur.fragments.light;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.lib.util.weeklyFragment;

public class LWeeklyFragment extends weeklyFragment
{
	public static LWeeklyFragment newInstance()
	{
		LWeeklyFragment fragment = new LWeeklyFragment();

		return fragment;
	}

	public LWeeklyFragment()
	{
	}

	@Override
	protected void setCategorie()
	{
		categorie = 1;
	}
}
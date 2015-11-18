package com.android.fishroom.bulbasaur.fragments.humidity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.lib.util.weeklyFragment;

public class HWeeklyFragment extends weeklyFragment
{
	public static HWeeklyFragment newInstance()
	{
		HWeeklyFragment fragment = new HWeeklyFragment();

		return fragment;
	}

	public HWeeklyFragment()
	{
	}

	@Override
	protected void setCategorie()
	{
		categorie = 2;
	}
}
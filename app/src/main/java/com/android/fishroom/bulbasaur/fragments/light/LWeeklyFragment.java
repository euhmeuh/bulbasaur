package com.android.fishroom.bulbasaur.fragments.light;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;

public class LWeeklyFragment extends Fragment
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
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_light_vp, container, false);
	}
}
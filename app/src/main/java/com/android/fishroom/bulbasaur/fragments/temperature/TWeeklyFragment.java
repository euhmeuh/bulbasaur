package com.android.fishroom.bulbasaur.fragments.temperature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;

public class TWeeklyFragment extends Fragment
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
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_temperature_vp, container, false);
	}
}
package com.android.fishroom.bulbasaur.fragments.temperature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;

public class TMonthlyFragment extends Fragment
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
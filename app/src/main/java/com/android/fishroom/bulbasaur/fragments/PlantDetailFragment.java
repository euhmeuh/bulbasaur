package com.android.fishroom.bulbasaur.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.HumidityVpActivity;
import com.android.fishroom.bulbasaur.LightVpActivity;
import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.TemperatureVpActivity;

public class PlantDetailFragment extends Fragment
{
	private View temperature;
	private View light;
	private View humidity;

	public PlantDetailFragment()
	{
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_plant_detail, container, false);

		temperature = view.findViewById(R.id.temperature);
		light = view.findViewById(R.id.light);
		humidity = view.findViewById(R.id.humidity);

		temperature.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), TemperatureVpActivity.class);
				startActivity(intent);
			}
		});

		light.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), LightVpActivity.class);
				startActivity(intent);
			}
		});

		humidity.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), HumidityVpActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}
}

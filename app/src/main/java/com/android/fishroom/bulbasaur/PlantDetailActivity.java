package com.android.fishroom.bulbasaur;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.android.fishroom.bulbasaur.fragments.PlantDetailFragment;

public class PlantDetailActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_detail);

		PlantDetailFragment fragment = new PlantDetailFragment();

		getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
	}
}

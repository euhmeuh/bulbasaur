package com.android.fishroom.bulbasaur;

import android.app.Activity;
import android.os.Bundle;

import com.android.fishroom.bulbasaur.fragments.PlantListFragment;


public class PlantListActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_list);

		PlantListFragment fragment = new PlantListFragment();

		getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
	}
}

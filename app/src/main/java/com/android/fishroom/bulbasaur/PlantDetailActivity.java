package com.android.fishroom.bulbasaur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android.fishroom.bulbasaur.model.Plant;
import com.android.fishroom.bulbasaur.fragments.PlantDetailFragment;

public class PlantDetailActivity extends FragmentActivity
{
	public static final String PLANT_ARG = "plant";

	private Toolbar toolbar;
	private PlantDetailFragment plantDetailFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_detail);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitleTextColor(getResources().getColor(R.color.white));
		toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
		toolbar.setNavigationOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

		toolbar.inflateMenu(R.menu.menu_logout);
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				int id = item.getItemId();

				if (id == R.id.action_logout)
				{
					logout();

					return true;
				}

				return false;
			}
		});

		if (getIntent().hasExtra(PLANT_ARG))
		{
			toolbar.setTitle(((Plant)getIntent().getSerializableExtra(PLANT_ARG)).getName());
		}

		if (savedInstanceState == null)
		{
			plantDetailFragment = new PlantDetailFragment();
			plantDetailFragment.setArguments(getIntent().getExtras());

			getFragmentManager().beginTransaction().add(R.id.container, plantDetailFragment).commit();
		}
	}

	private void logout()
	{
		SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();

		editor.remove(LoginActivity.PREFS_USER_ID).apply();

		Intent intent = new Intent(this, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
}

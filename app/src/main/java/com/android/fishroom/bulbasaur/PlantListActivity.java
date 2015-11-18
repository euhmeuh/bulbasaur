package com.android.fishroom.bulbasaur;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.fishroom.bulbasaur.fragments.PlantListFragment;


public class PlantListActivity extends Activity
{
	public static final String USER_ARG = "user";

	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plant_list);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitleTextColor(getResources().getColor(R.color.white));
		toolbar.setTitle("Liste des plantes");

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

		if (savedInstanceState == null)
		{
			PlantListFragment fragment = new PlantListFragment();
			fragment.setArguments(getIntent().getExtras());

			getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
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

package com.android.fishroom.bulbasaur;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.fishroom.bulbasaur.fragments.temperature.TDailyFragment;
import com.android.fishroom.bulbasaur.model.User;
import com.android.fishroom.bulbasaur.test.testActivity;

public class BootActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot);

		SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);

//		if (settings.contains(LoginActivity.PREFS_USER_ID))
//		{
//			Intent intent = new Intent(this, PlantListActivity.class);
//			intent.putExtra(PlantListActivity.USER_ARG, new User(settings.getString(LoginActivity.PREFS_USER_ID, null)));
//			startActivity(intent);
//			finish();
//
//			return;
//		}
//		else
//		{
//			Intent intent = new Intent(this, LoginActivity.class);
//			startActivity(intent);
//			finish();
//		}

		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}

package com.android.fishroom.bulbasaur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BootActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot);

		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}

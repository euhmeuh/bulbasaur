package com.android.fishroom.bulbasaur;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends ActionBarActivity
{
	private Button validate;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		validate = (Button) findViewById(R.id.validate);

		validate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), PlantListActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}

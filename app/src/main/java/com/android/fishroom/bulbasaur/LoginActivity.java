package com.android.fishroom.bulbasaur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.fishroom.bulbasaur.Model.Plant;

public class LoginActivity extends Activity
{
	private Plant plant;

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
				Intent intent = new Intent(v.getContext(), PlantDetailActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}

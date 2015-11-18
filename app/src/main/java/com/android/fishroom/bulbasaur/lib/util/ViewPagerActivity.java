package com.android.fishroom.bulbasaur.lib.util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android.fishroom.bulbasaur.LoginActivity;
import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.controller.adapter.ViewPagerAdapter;
import com.android.fishroom.bulbasaur.lib.util.SlidingTabLayout;

public class ViewPagerActivity extends FragmentActivity
{
	protected ViewPagerAdapter adapterViewPager;
	protected Toolbar toolbar;
	protected ViewPager viewPager;
	protected SlidingTabLayout slidingTabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);

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

		slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
		slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
		slidingTabLayout.setCustomTabView(R.layout.tab_textview, R.id.textview);
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

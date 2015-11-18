package com.android.fishroom.bulbasaur.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.util.Log;
import android.view.Gravity;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.model.Plant;

import java.util.List;

public class ElementGridPagerAdapter extends FragmentGridPagerAdapter
{


	private Page[][] pages;

	public ElementGridPagerAdapter(FragmentManager fm, List<Plant> plantList)
	{
		super(fm);

		pages = new Page[plantList.size()][4];

		for (int x = 0; x < plantList.size(); x++)
		{
			for (int y = 0; y < 4; y++)
			{
				switch (y)
				{
					case 0:
						pages[x][y] = new Page(plantList.get(x).getName(), plantList.get(x).getDescription());
						break;

					case 1:
						pages[x][y] = new Page("Température", String.valueOf(plantList.get(x).getTemperature() + "C°"));
						break;

					case 2:
						pages[x][y] = new Page("Luminosité", String.valueOf(plantList.get(x).getLight()));
						break;

					case 3:
						pages[x][y] = new Page("Humidité", String.valueOf(plantList.get(x).getHumidity()));
						break;
				}

			}
		}
	}


//	private final Page[][] PAGES = {
//			{
//					new Page("Dallas/Fort Worth", "Local Time : 11:00"),
//					new Page("IA656", "Departed"),
//					new Page("IA634", "Boarding"),
//					new Page("IA087", "Delayed 30 min"),
//			},
//			{
//					new Page("Denver", "Local Time : 10:00"),
//					new Page("IA001", "Delayed 30 min"),
//					new Page("IA364", "Departed"),
//					new Page("IA817", "Boarding"),
//			},
//			{
//					new Page("San Fransisco", "Local Time : 10:00"),
//					new Page("IA878", "Boarding"),
//					new Page("IA656", "Departed"),
//					new Page("IA256", "Delayed 30 min"),
//			},
//
//	};


	@Override
	public Fragment getFragment(int row, int col)
	{
		Page page = pages[row][col];
		String title = page.title;
		String text = page.detail;
		CardFragment fragment;

		Log.e("test", String.valueOf(col));

		switch (col)
		{
			case 0:
				fragment = CardFragment.create(title, text, R.drawable.marginata);
				break;

			case 1:
				fragment = CardFragment.create(title, text, R.drawable.ic_temperature);
				break;

			case 2:
				fragment = CardFragment.create(title, text, R.drawable.ic_light_bulb);
				break;

			case 3:
				fragment = CardFragment.create(title, text, R.drawable.ic_humidity);
				break;

			default:
				fragment = CardFragment.create(title, text, R.mipmap.ic_launcher);
				break;
		}

		fragment.setCardGravity(page.cardGravity);
		fragment.setExpansionEnabled(page.expansionEnabled);
		fragment.setExpansionDirection(page.expansionDirection);
		fragment.setExpansionFactor(page.expansionFactor);
		return fragment;
	}

	@Override
	public Drawable getBackgroundForPage(int row, int column)
	{
		switch (column)
		{
			case 0:
				return new ColorDrawable(R.color.colorPrimary);

			case 1:
				return new ColorDrawable(R.color.temperature_background);

			case 2:
				return new ColorDrawable(R.color.light_background);

			case 3:
				return new ColorDrawable(R.color.humidity_background);

			default:
				return new ColorDrawable(R.color.colorPrimary);
		}
	}

	@Override
	public int getRowCount()
	{
		return pages.length;
	}

	@Override
	public int getColumnCount(int rowNum)
	{
		return pages[rowNum].length;
	}

	/**
	 * A simple container for static data in each page
	 */
	private static class Page
	{
		String title;
		String detail;
		int cardGravity = Gravity.BOTTOM;
		boolean expansionEnabled = false;
		float expansionFactor = 1.0f;
		int expansionDirection = CardFragment.EXPAND_DOWN;

		public Page(String title, String detail)
		{
			this.title = title;
			this.detail = detail;
		}
	}
}

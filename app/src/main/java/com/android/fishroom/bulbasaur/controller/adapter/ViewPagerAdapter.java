package com.android.fishroom.bulbasaur.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public class ViewPagerAdapter extends FragmentPagerAdapter
	{
		private static final int NUM_ITEMS = 3;

		private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

		public ViewPagerAdapter(FragmentManager fragmentManager)
		{
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position)
		{
			return null;
		}

		@Override
		public int getCount()
		{
			return NUM_ITEMS;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			Fragment fragment = (Fragment) super.instantiateItem(container, position);
			registeredFragments.put(position, fragment);
			return fragment;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			registeredFragments.remove(position);
			super.destroyItem(container, position, object);
		}

	public Fragment getRegisteredFragment(int position)
	{
		return registeredFragments.get(position);
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		switch (position)
		{
			case 0:
				return "Aujourd'hui";

			case 1:
				return "Semaine";

			case 2:
				return "Mois";
		}

		return "Page " + position;
	}
}

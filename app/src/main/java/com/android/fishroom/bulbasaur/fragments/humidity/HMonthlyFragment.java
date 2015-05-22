package com.android.fishroom.bulbasaur.fragments.humidity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;

public class HMonthlyFragment extends Fragment
{
	public static HMonthlyFragment newInstance()
	{
		HMonthlyFragment fragment = new HMonthlyFragment();

		return fragment;
	}

	public HMonthlyFragment()
	{
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_humidity_vp, container, false);
	}
}
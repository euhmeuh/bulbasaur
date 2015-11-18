package com.android.fishroom.bulbasaur.fragments.humidity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.lib.util.MonthlyFragment;

public class HMonthlyFragment extends MonthlyFragment
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
	protected void setCategorie()
	{
		categorie = 2;
	}
}
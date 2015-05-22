package com.android.fishroom.bulbasaur.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.fishroom.bulbasaur.Model.Plant;
import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.controller.handler.PlantsResponseHandler;
import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.android.fishroom.bulbasaur.lib.manager.APIManager;
import com.android.fishroom.bulbasaur.lib.manager.ResponseManager;

public class PlantListFragment extends Fragment implements Response<SerializableSparseArray<Plant>>
{
	private static final String PLANTS_LIST = "plants_list";

	private TextView plants;

	public PlantListFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_plant_list, container, false);

		plants = (TextView) view.findViewById(R.id.plants);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		ResponseManager.getInstance().attach(PLANTS_LIST, this);

		PlantsResponseHandler response = new PlantsResponseHandler();
		APIManager.getInstance().getPlants(response);
		ResponseManager.getInstance().addResponse(PLANTS_LIST, response, this);
	}

	@Override
	public void handleResponse(@NonNull SerializableSparseArray<Plant> data, boolean more)
	{

	}

	@Override
	public void handleError()
	{

	}
}

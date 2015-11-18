package com.android.fishroom.bulbasaur.fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.fishroom.bulbasaur.LoginActivity;
import com.android.fishroom.bulbasaur.PlantListActivity;
import com.android.fishroom.bulbasaur.controller.adapter.PlantAdapter;
import com.android.fishroom.bulbasaur.controller.handler.PlantResponseHandler;
import com.android.fishroom.bulbasaur.controller.handler.TestResponseHandler;
import com.android.fishroom.bulbasaur.model.Plant;
import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.controller.handler.PlantsResponseHandler;
import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.android.fishroom.bulbasaur.lib.manager.APIManager;
import com.android.fishroom.bulbasaur.lib.manager.ResponseManager;
import com.android.fishroom.bulbasaur.model.User;

public class PlantListFragment extends Fragment implements Response<Plant>
{
	public static final String PLANT_ID = "plant_id";
	private static final String PLANTS_LIST = "plants_list";
	private static final String INSTANCE_LIST = "instance_list";
	private static final String PLANT_CONNECT = "plant_connect";

	private User user;

	private RecyclerView recyclerView;

	private PlantAdapter plantAdapter;
	private ProgressDialog progressDialog;

	public PlantListFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_plant_list, container, false);

		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

		progressDialog = new ProgressDialog(getActivity());
		LinearLayoutManager llm = new LinearLayoutManager(getActivity());
		llm.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(llm);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState != null)
		{
			Object item = savedInstanceState.get(INSTANCE_LIST);

			if (item instanceof SerializableSparseArray)
			{
				plantAdapter = new PlantAdapter((SerializableSparseArray<Plant>) item);
				recyclerView.setAdapter(plantAdapter);

				return;
			}
		}

		Bundle args = getArguments();

		progressDialog.setMessage("Chargement...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(true);


		if (args != null && args.containsKey(PlantListActivity.USER_ARG))
		{
			user = (User) args.getSerializable(PlantListActivity.USER_ARG);
			Log.e("test", String.valueOf(user.getPlants()));

			if (user != null)
			{
				SharedPreferences settings = getActivity().getSharedPreferences(LoginActivity.PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(PLANT_ID, user.getPlants().getId()).apply();

				sendRequest(user.getId());
			}
		}
	}

	public void sendRequest(String plantId)
	{
		progressDialog.show();

		SerializableSparseArray<Plant> plants = new SerializableSparseArray<>(1);

		Plant test = new Plant();
		test.setId("0");
		test.setName("Dragonnier de Madagascar");
		test.setType("Dracaena marginata");

		plants.put(0, test);

		Log.e("test", String.valueOf(plants.size()));

		plantAdapter = new PlantAdapter(plants);
		recyclerView.setAdapter(plantAdapter);

		progressDialog.dismiss();

//		TestResponseHandler response = new TestResponseHandler();
//		APIManager.getInstance().getUserPlants(response, plantId);
//		ResponseManager.getInstance().addResponse(PLANT_CONNECT, response, this);
	}

	@Override public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);

		if (plantAdapter != null)
		{
			outState.putSerializable(INSTANCE_LIST, plantAdapter.getPlantList());
		}
	}

	@Override
	public void handleResponse(Plant data, boolean more)
	{
		Log.e("test", String.valueOf(data));
		SerializableSparseArray<Plant> plants = new SerializableSparseArray<>(1);

		plants.put(0, data);

		Log.e("test", String.valueOf(plants.size()));

		plantAdapter = new PlantAdapter(plants);
		recyclerView.setAdapter(plantAdapter);

		progressDialog.dismiss();
	}

	@Override
	public void handleError()
	{
		progressDialog.dismiss();
		Toast.makeText(getActivity(), "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
	}
}

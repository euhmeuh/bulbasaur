package com.android.fishroom.bulbasaur.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fishroom.bulbasaur.HumidityVpActivity;
import com.android.fishroom.bulbasaur.LightVpActivity;
import com.android.fishroom.bulbasaur.PlantDetailActivity;
import com.android.fishroom.bulbasaur.controller.handler.PlantResponseHandler;
import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.lib.manager.APIManager;
import com.android.fishroom.bulbasaur.lib.manager.ResponseManager;
import com.android.fishroom.bulbasaur.model.Plant;
import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.TemperatureVpActivity;

public class PlantDetailFragment extends Fragment implements Response<Plant>
{
	private static final String PLANT_DETAIL = "plant_detail";

	private TextView temperature;
	private TextView light;
	private TextView humidity;

	private View temperatureView;
	private View lightView;
	private View humidityView;

	private Plant plant;

	private ProgressDialog progressDialog;

	public PlantDetailFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_plant_detail, container, false);

		temperature = (TextView) view.findViewById(R.id.temperature);
		light = (TextView) view.findViewById(R.id.light);
		humidity = (TextView) view.findViewById(R.id.humidity);

		temperatureView = view.findViewById(R.id.temperature_view);
		lightView = view.findViewById(R.id.light_view);
		humidityView = view.findViewById(R.id.humidity_view);

		progressDialog = new ProgressDialog(getActivity());

		temperatureView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), TemperatureVpActivity.class);
				startActivity(intent);
			}
		});

		lightView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), LightVpActivity.class);
				startActivity(intent);
			}
		});

		humidityView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), HumidityVpActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();

		if (args != null && args.containsKey(PlantDetailActivity.PLANT_ARG))
		{
			plant = (Plant) args.getSerializable(PlantDetailActivity.PLANT_ARG);

			if (plant != null)
			{
				sendRequest(plant.getId());
			}
		}

		progressDialog.setMessage("Chargement...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(true);
	}

	public void sendRequest(String plantId)
	{
		progressDialog.show();

		PlantResponseHandler response = new PlantResponseHandler();
		APIManager.getInstance().getPlant(response, plantId);
		ResponseManager.getInstance().addResponse(PLANT_DETAIL, response, this);
	}

	@Override
	public void onDetach()
	{
		super.onDetach();
	}

	@Override
	public void handleResponse(Plant data, boolean more)
	{
		progressDialog.dismiss();

		temperature.setText(String.valueOf(data.getTemperature() + "°C"));
		light.setText(String.valueOf(data.getLight() + "%"));
		humidity.setText(String.valueOf(data.getHumidity() + "%"));
	}

	@Override
	public void handleError()
	{
		progressDialog.dismiss();
		Toast.makeText(getActivity(), "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
	}
}

package com.android.fishroom.bulbasaur.controller.handler;

import com.android.fishroom.bulbasaur.model.Plant;
import com.android.fishroom.bulbasaur.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PlantResponseHandler extends ResponseHandler
{
	private Plant plant;

	@Override public void onSuccess()
	{
		super.onSuccess();

		JsonArray plantTest = getContent().getAsJsonObject().get("rows").getAsJsonArray();
		JsonObject plantObject = plantTest.get(0).getAsJsonObject();

		Gson gson = new GsonBuilder().create();
		plant = gson.fromJson(plantObject.get("value"), Plant.class);
	}

	@Override public void onFinish(boolean failed)
	{
		super.onFinish(failed);

		if (!failed)
		{
			getResponse().handleResponse(plant, false);
		}
		else
		{
			getResponse().handleError();
		}
	}
}

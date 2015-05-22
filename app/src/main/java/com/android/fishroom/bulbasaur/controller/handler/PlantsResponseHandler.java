package com.android.fishroom.bulbasaur.controller.handler;

import com.android.fishroom.bulbasaur.Model.Plant;
import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PlantsResponseHandler extends ResponseHandler
{
	private SerializableSparseArray<Plant> plants = new SerializableSparseArray<Plant>(1);

	@Override public void onSuccess()
	{
		super.onSuccess();

		plants.clear();
		JsonArray plantsArray = getContent().getAsJsonObject().get("plants").getAsJsonArray();

		for (int index = 0; index < plantsArray.size(); index++)
		{
			JsonObject storyObject = plantsArray.get(index).getAsJsonObject();
			Gson gson = new GsonBuilder().create();
			plants.put(index, gson.fromJson(storyObject, Plant.class));
		}
	}

	@Override public void onFinish(boolean failed)
	{
		super.onFinish(failed);

		if (!failed)
		{
			getResponse().handleResponse(plants, false);
		}
		else
		{
		}
	}
}


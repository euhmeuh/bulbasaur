package com.android.fishroom.bulbasaur.controller.handler;

import com.android.fishroom.bulbasaur.model.Plant;
import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

		plants.clear();

		String test = "{\n" +
				"    \"total_rows\": 3,\n" +
				"    \"offset\": 0,\n" +
				"    \"rows\": [\n" +
				"        {\n" +
				"            \"id\": \"f20c9f142bd8d7a3f75db4aff20000cd\",\n" +
				"            \"key\": [\n" +
				"                0\n" +
				"            ],\n" +
				"            \"value\": {\n" +
				"                \"id_plant\": 0,\n" +
				"                \"latin_name\": \"Dracaena marginata\",\n" +
				"                \"name_plant\": \"Dragonnier de Madagascar\"\n" +
				"            }\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject)parser.parse(test);

		JsonArray plantsArray = object.get("plants").getAsJsonArray();

		for (int index = 0; index < plantsArray.size(); index++)
		{
			JsonObject storyObject = plantsArray.get(index).getAsJsonObject();
			Gson gson = new GsonBuilder().create();
			plants.put(index, gson.fromJson(storyObject, Plant.class));
		}

		getResponse().handleResponse(plants, false);

//		if (!failed)
//		{
//			getResponse().handleResponse(plants, false);
//		}
//		else
//		{
//		}
	}
}


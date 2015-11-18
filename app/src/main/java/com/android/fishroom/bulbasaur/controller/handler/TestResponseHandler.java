package com.android.fishroom.bulbasaur.controller.handler;

import com.android.fishroom.bulbasaur.model.Plant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestResponseHandler extends ResponseHandler
{
	private Plant plant;

	@Override public void onSuccess()
	{
		super.onSuccess();

//		JsonArray plantTest = getContent().getAsJsonObject().get("rows").getAsJsonArray();
//		JsonObject plantObject = plantTest.get(0).getAsJsonObject();
//
//		Gson gson = new GsonBuilder().create();
//		plant = gson.fromJson(plantObject.get("value"), Plant.class);
	}

	@Override public void onFinish(boolean failed)
	{
		super.onFinish(failed);

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

		JsonArray plantTest = object.get("rows").getAsJsonArray();
		JsonObject plantObject = plantTest.get(0).getAsJsonObject();

		Gson gson = new GsonBuilder().create();
		plant = gson.fromJson(plantObject.get("value"), Plant.class);

//		if (!failed)
//		{
//			getResponse().handleResponse(plant, false);
//		}
//		else
//		{
//			getResponse().handleError();
//		}
	}
}
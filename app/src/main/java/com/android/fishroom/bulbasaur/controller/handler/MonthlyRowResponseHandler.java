package com.android.fishroom.bulbasaur.controller.handler;

import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.android.fishroom.bulbasaur.model.Rows;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MonthlyRowResponseHandler extends ResponseHandler
{
	private SerializableSparseArray<Rows> rows = new SerializableSparseArray<Rows>(1);

	@Override public void onSuccess()
	{
		super.onSuccess();

		rows.clear();
		JsonArray rowsArray = getContent().getAsJsonObject().get("rows").getAsJsonArray();

		for (int index = 0; index < rowsArray.size(); index++)
		{
			JsonObject storyObject = rowsArray.get(index).getAsJsonObject();
			Gson gson = new GsonBuilder().create();
			rows.put(index, gson.fromJson(storyObject, Rows.class));
		}
	}

	@Override public void onFinish(boolean failed)
	{
		super.onFinish(failed);

		if (!failed)
		{
			getResponse().handleResponse(rows, false);
		}
		else
		{
		}
	}
}
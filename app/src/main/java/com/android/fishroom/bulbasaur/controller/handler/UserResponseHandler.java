package com.android.fishroom.bulbasaur.controller.handler;

import android.util.Log;

import com.android.fishroom.bulbasaur.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UserResponseHandler extends ResponseHandler
{
	private User user;

	@Override public void onSuccess()
	{
		super.onSuccess();

		Log.e("test", String.valueOf(getContent()));

//		JsonObject userObject = getContent().getAsJsonObject().get("value").getAsJsonObject();

		JsonArray userTest = getContent().getAsJsonObject().get("rows").getAsJsonArray();
		Log.e("test2", String.valueOf(userTest));

		if(userTest.size() > 0)
		{
			JsonObject userObject = userTest.get(0).getAsJsonObject();

			Gson gson = new GsonBuilder().create();
			user = gson.fromJson(userObject.get("value"), User.class);
		}
	}

	@Override public void onFinish(boolean failed)
	{
		super.onFinish(failed);

		if (!failed && user != null)
		{
			getResponse().handleResponse(user, false);
		}
		else
		{
			getResponse().handleError();
		}
	}
}
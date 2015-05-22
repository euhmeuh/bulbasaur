package com.android.fishroom.bulbasaur.lib.manager;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.AsyncHttpResponseHandler;

public class APIManager
{
	private static APIManager INSTANCE;
	private static final String API_URL = "";
	private static final String PLANT = "";

	public static APIManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new APIManager();
		}
		return INSTANCE;
	}
	public AsyncHttpClient getPlants(AsyncHttpResponseHandler response)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);
		client.get(PLANT, response);
		return client;
	}
}

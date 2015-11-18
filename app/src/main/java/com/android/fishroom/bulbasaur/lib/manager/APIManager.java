package com.android.fishroom.bulbasaur.lib.manager;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.AsyncHttpResponseHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class APIManager
{
	private static APIManager INSTANCE;
	private static final String API_URL = "http://10.144.128.79:5984/bulbasaur_replicate/_design/";

	public static APIManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new APIManager();
		}
		return INSTANCE;
	}
	String relou = "&stale=ok";

	public AsyncHttpClient getDaily(AsyncHttpResponseHandler response)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);
		client.get("plants/_view/get_last_day?group=true&limit=24&descending=true", response);
//		client.get("", response);
		return client;
	}

	public AsyncHttpClient getWeekly(AsyncHttpResponseHandler response)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);
		client.get("plants/_view/get_last_week?group=true&limit=7&descending=true", response);
//		client.get("", response);
		return client;
	}

	public AsyncHttpClient getMonthly(AsyncHttpResponseHandler response)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);
		client.get("plants/_view/get_last_week?group=true&limit=30&descending=true", response);
//		client.get("", response);
		return client;
	}

	public AsyncHttpClient getUserPlants(AsyncHttpResponseHandler response, String userId)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);
		client.get("plants/_view/get_plants?key=[" + userId + "]" + relou, response);
		return client;
	}

	public AsyncHttpClient getPlant(AsyncHttpResponseHandler response, String plantId)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);
		client.get("plants/_view/get_ld?descending=true&limit=1", response);
		return client;
	}

	public AsyncHttpClient getPlantValue(AsyncHttpResponseHandler response, String plantId, String time)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("plantID", plantId));
		params.add(new BasicNameValuePair("time", time));

		client.get("", response);
		return client;
	}

	public AsyncHttpClient UserConnect(AsyncHttpResponseHandler response, String login, String password)
	{
		AsyncHttpClient client = new AsyncHttpClient(API_URL);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("login", login));
//		params.add(new BasicNameValuePair("password", password));

		client.get("login/_view/get_login?key=[%22" + login + "%22,%22" + password + "%22]" + relou, response);
//		client.get("login/_view/get_login?key=[%222fat%22,%22azerty%22]", response);
		return client;
	}
}

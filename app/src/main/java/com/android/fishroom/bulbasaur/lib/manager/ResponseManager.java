package com.android.fishroom.bulbasaur.lib.manager;

import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.controller.handler.ResponseHandler;

import java.lang.ref.WeakReference;
import java.util.HashMap;


public class ResponseManager
{
	private static ResponseManager instance;

	public static ResponseManager getInstance()
	{
		if (instance == null)
		{
			synchronized (ResponseManager.class)
			{
				if (instance == null)
				{
					instance = new ResponseManager();
				}
			}
		}

		return instance;
	}

	public final HashMap<String, WeakReference<ResponseHandler>> responses = new HashMap<String, WeakReference<ResponseHandler>>();

	private ResponseManager()
	{

	}

	public ResponseHandler getResponse(String key)
	{
		return responses.get(key) == null ? null : responses.get(key).get();
	}

	public void addResponse(String key, ResponseHandler handler, Response response)
	{
		detach(key);
		responses.put(key, new WeakReference(handler));
		handler.setResponseKey(key);

		if (response != null)
		{
			handler.attach(response);
		}
	}

	public boolean attach(String key, Response response)
	{
		if (responses.containsKey(key) && responses.get(key) != null && responses.get(key).get() != null)
		{
			responses.get(key).get().attach((Response)response);
			return true;
		}

		return false;
	}

	public boolean detach(String key)
	{
		if (responses.containsKey(key) && responses.get(key) != null && responses.get(key).get() != null)
		{
			responses.get(key).get().detach();
			return true;
		}

		return false;
	}

	public void removeResponse(String key)
	{
		detach(key);
		responses.remove(key);
	}
}

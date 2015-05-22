package com.android.fishroom.bulbasaur.controller.handler;

import android.util.Log;

import net.callumtaylor.asynchttp.response.JsonResponseHandler;
import java.lang.reflect.Field;

import lombok.Getter;
import lombok.Setter;

public class ResponseHandler extends JsonResponseHandler
{
	public static interface OnFragmentAttachedListener
	{
		public void onFragmentAttached(Response fragment);
	}

	@Getter @Setter private OnFragmentAttachedListener onFragmentAttachedListener;
	@Getter @Setter private String responseKey = "";
	@Getter private Response response;

	@Override public void generateContent()
	{
		try
		{
			super.generateContent();
		}
		catch (Exception e)
		{
			try
			{
				Field str = JsonResponseHandler.class.getDeclaredField("stringBuffer");
				str.setAccessible(true);

				StringBuffer buffer = (StringBuffer)str.get(this);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	@Override public void onSuccess()
	{

	}

	@Override public void onFinish(final boolean failed)
	{
		super.onFinish(failed);

		if (failed)
		{
			Log.e("onFinishRepHandler", "Error");

			if (getResponse() == null)
			{
				setOnFragmentAttachedListener(new OnFragmentAttachedListener()
				{
					@Override public void onFragmentAttached(Response response)
					{
						onFinish(failed);
					}
				});
			}
		}
	}

	public void attach(Response response)
	{
		this.response = response;

		if (onFragmentAttachedListener != null)
		{
			onFragmentAttachedListener.onFragmentAttached(response);
		}
	}

	public void detach()
	{
		this.response = null;
	}
}

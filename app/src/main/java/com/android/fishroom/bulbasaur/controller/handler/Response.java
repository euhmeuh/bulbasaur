package com.android.fishroom.bulbasaur.controller.handler;

public interface Response<T>
{
	public void handleResponse(T data, boolean more);

	public void handleError();
}

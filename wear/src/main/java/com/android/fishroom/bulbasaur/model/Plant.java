package com.android.fishroom.bulbasaur.model;

import lombok.Data;

@Data
public class Plant
{
	private String name;
	private String description;

	private float temperature;
	private float light;
	private float humidity;

	public Plant(String name, String description, float temperature, float light, float humidity)
	{
		this.name = name;
		this.description = description;
		this.temperature = temperature;
		this.light = light;
		this.humidity = humidity;
	}
}

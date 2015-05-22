package com.android.fishroom.bulbasaur.Model;

import lombok.Data;

@Data
public class Plant extends Model
{
	private String id;
	private String name;
	private String type;

	private TemperatureData temperature;
	private LightData light;
	private HumidityData humidity;
}

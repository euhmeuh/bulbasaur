package com.android.fishroom.bulbasaur.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

@lombok.Data
public class Plant extends Model
{
	@SerializedName("id_plant")private String id;
	@SerializedName("name_plant")private String name;
	@SerializedName("latin_name")private String type;

	@SerializedName("T")private float temperature;
	@SerializedName("L")private float light;
	@SerializedName("H")private float humidity;
}

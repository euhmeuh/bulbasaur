package com.android.fishroom.bulbasaur.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Data;

@Data
public class Values extends Model
{
	@SerializedName("T")private float temp;
	@SerializedName("L")private float light;
	@SerializedName("H")private float humidity;
	private Date date;
}

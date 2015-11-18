package com.android.fishroom.bulbasaur.model;

import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.Getter;

@Data
public class User extends Model
{
	@SerializedName("id_user")private String id;
	private String login;
	private Plant plants;

	public User(String id)
	{
		this.id = id;
	}
}

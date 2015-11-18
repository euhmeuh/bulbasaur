package com.android.fishroom.bulbasaur;

import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.android.fishroom.bulbasaur.model.Rows;

import lombok.Getter;
import lombok.Setter;

public class RowSingleton
{
	private static RowSingleton singletonInstance;
	@Getter @Setter
	private SerializableSparseArray<Rows> dailyRows;
	@Getter @Setter
	private SerializableSparseArray<Rows> weeklyRows;
	@Getter @Setter
	private SerializableSparseArray<Rows> monthlyRows;

	private RowSingleton()
	{
	}

	public static RowSingleton getInstance()
	{
		if (null == singletonInstance)
		{
			singletonInstance = new RowSingleton();
		}
		return singletonInstance;
	}


}

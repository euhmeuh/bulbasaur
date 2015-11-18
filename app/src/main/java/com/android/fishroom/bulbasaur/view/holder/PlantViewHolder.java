package com.android.fishroom.bulbasaur.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.fishroom.bulbasaur.R;

public class PlantViewHolder extends RecyclerView.ViewHolder
{
	public TextView name;
	public TextView type;
	public View plantView;

	public PlantViewHolder(View itemView)
	{
		super(itemView);

		name = (TextView) itemView.findViewById(R.id.name);
		type = (TextView) itemView.findViewById(R.id.type);
		plantView = itemView.findViewById(R.id.plant_view);
	}
}

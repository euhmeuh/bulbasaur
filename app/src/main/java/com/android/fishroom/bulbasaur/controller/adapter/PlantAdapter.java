package com.android.fishroom.bulbasaur.controller.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.PlantDetailActivity;
import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.android.fishroom.bulbasaur.model.Plant;
import com.android.fishroom.bulbasaur.view.holder.PlantViewHolder;

import lombok.Getter;

public class PlantAdapter extends RecyclerView.Adapter<PlantViewHolder>
{
	@Getter
	private SerializableSparseArray<Plant> plantList;

	public PlantAdapter(SerializableSparseArray<Plant> plantList)
	{
		this.plantList = plantList;
	}

	@Override
	public PlantViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
	{
		View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_plant, viewGroup, false);

		return new PlantViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(PlantViewHolder plantViewHolder, int i)
	{
		final Plant plant = plantList.get(i);

		plantViewHolder.name.setText(plant.getName());
		plantViewHolder.type.setText(plant.getType());

		plantViewHolder.plantView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), PlantDetailActivity.class);
				intent.putExtra(PlantDetailActivity.PLANT_ARG, plant);
				v.getContext().startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount()
	{
		return plantList.size();
	}
}

package com.android.fishroom.bulbasaur.wear;

import android.util.Log;

import com.android.fishroom.bulbasaur.controller.handler.PlantResponseHandler;
import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.lib.manager.APIManager;
import com.android.fishroom.bulbasaur.lib.manager.ResponseManager;
import com.android.fishroom.bulbasaur.model.Plant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WearService extends WearableListenerService implements Response<Plant>
{
	private static final String PLANT_CONNECT_WEAR = "plant_connect_wear";
	private static final String PLANT_CONNECT_WEAR2 = "plant_connect_wear2";
	private final static String TAG = WearService.class.getCanonicalName();

	protected GoogleApiClient mApiClient;

	private Plant plant = new Plant();

	private int request = 0;

	@Override
	public void onCreate()
	{
		super.onCreate();
		mApiClient = new GoogleApiClient.Builder(this)
				.addApi(Wearable.API)
				.build();
		mApiClient.connect();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mApiClient.disconnect();
	}

	/**
	 * Appellé à la réception d'un message envoyé depuis la montre
	 *
	 * @param messageEvent message reçu
	 */
	@Override
	public void onMessageReceived(MessageEvent messageEvent)
	{
		super.onMessageReceived(messageEvent);

		//Ouvre une connexion vers la montre
		ConnectionResult connectionResult = mApiClient.blockingConnect(30, TimeUnit.SECONDS);

		if (!connectionResult.isSuccess())
		{
			Log.e(TAG, "Failed to connect to GoogleApiClient.");
			return;
		}

		//traite le message reçu
		final String path = messageEvent.getPath();


		sendSecondRequest("0");
	}

	public void sendFirstRequest(String userId)
	{
		PlantResponseHandler response = new PlantResponseHandler();
		APIManager.getInstance().getUserPlants(response, userId);
		ResponseManager.getInstance().addResponse(PLANT_CONNECT_WEAR, response, this);
	}

	public void sendSecondRequest(String plantId)
	{
		PlantResponseHandler response = new PlantResponseHandler();
		APIManager.getInstance().getPlant(response, plantId);
		ResponseManager.getInstance().addResponse(PLANT_CONNECT_WEAR2, response, this);
	}

	private void sendPlantsList(final Plant plant)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				sendPlants(plant);
			}
		}).start();
	}

	protected void sendPlants(final Plant plantData)
	{
		Log.e("test", "in SendPlants");
		final PutDataMapRequest putDataMapRequest = PutDataMapRequest.create("/plants/");

		ArrayList<DataMap> plantsDataMap = new ArrayList<>();

		DataMap plantDataMap = new DataMap();

		//créé un emplacement mémoire "element/[position]"

		//ajoute la date de mi[jase à jour
		plantDataMap.putString("timestamp", new Date().toString());

		//ajoute la plante champ par champ
		plantDataMap.putString("name", plantData.getName());
		plantDataMap.putString("description", plantData.getType());
		plantDataMap.putFloat("temperature", plantData.getTemperature());
		plantDataMap.putFloat("light", plantData.getLight());
		plantDataMap.putFloat("humidity", plantData.getHumidity());

		plantsDataMap.add(plantDataMap);

		//place la liste dans la datamap envoyée à la wear
		putDataMapRequest.getDataMap().putDataMapArrayList("/list/",plantsDataMap);

		//envoie la liste à la montre
		if (mApiClient.isConnected())
		{
			Wearable.DataApi.putDataItem(mApiClient, putDataMapRequest.asPutDataRequest());
		}
	}

	/**
	 * Envoie un message à la montre
	 *
	 * @param path    identifiant du message
	 * @param message message à transmettre
	 */
	protected void sendMessage(final String path, final String message)
	{
		//effectué dans un trhead afin de ne pas être bloquant
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				//envoie le message à tous les noeuds/montres connectées
				final NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(mApiClient).await();
				for (Node node : nodes.getNodes())
				{
					Wearable.MessageApi.sendMessage(mApiClient, node.getId(), path, message.getBytes()).await();

				}
			}
		}).start();
	}

	@Override
	public void handleResponse(Plant data, boolean more)
	{
		plant.setName("retest");
		plant.setType("test");

		plant.setTemperature(data.getTemperature());
		plant.setLight(data.getLight());
		plant.setHumidity(data.getHumidity());

		sendPlantsList(plant);
	}

	@Override
	public void handleError()
	{

	}
}
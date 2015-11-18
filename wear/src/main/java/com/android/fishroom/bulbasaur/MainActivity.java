package com.android.fishroom.bulbasaur;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.widget.TextView;

import com.android.fishroom.bulbasaur.adapter.ElementGridPagerAdapter;
import com.android.fishroom.bulbasaur.model.Plant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, MessageApi.MessageListener, DataApi.DataListener
{

	private GridViewPager pager;
	private DotsPageIndicator dotsPageIndicator;

	private List<Plant> plantList;

	protected GoogleApiClient mApiClient;

	private TextView test;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		test = (TextView) findViewById(R.id.test);
		pager = (GridViewPager) findViewById(R.id.pager);
		dotsPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
		dotsPageIndicator.setPager(pager);

		//plantList = creerListElements();

		//pager.setAdapter(new ElementGridPagerAdapter(getFragmentManager(), plantList));
	}

//	private List<Plant> creerListElements()
//	{
//		List<String> list = new ArrayList<>();
//
//		list.add("aze");
//		list.add("tre");
//		list.add("azrsd");
//
//		return list;
//	}

	/**
	 * A l'ouverture, connecte la montre au Google API Client / donc au smartphone
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		mApiClient = new GoogleApiClient.Builder(this)
				.addApi(Wearable.API)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.build();

		mApiClient.connect();
	}

	/**
	 * Si nous avons une connection aux Google API, donc au smartphone
	 * Nous autorisons l'envoie de messages
	 */
	@Override
	public void onConnected(Bundle bundle)
	{
		Wearable.MessageApi.addListener(mApiClient, this);
		Wearable.DataApi.addListener(mApiClient, this);
		//envoie le premier message
		sendMessage("bonjour", "smartphone");

		Log.e("test", "test");

	}

	/**
	 * A la fermeture de l'application, desactive le GoogleApiClient
	 * Et ferme l'envoie de message
	 */
	@Override
	protected void onStop()
	{
		if (null != mApiClient && mApiClient.isConnected())
		{
			Wearable.MessageApi.removeListener(mApiClient, this);
			mApiClient.disconnect();
		}
		super.onStop();
	}

	@Override
	public void onConnectionSuspended(int i)
	{
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult)
	{
	}

	@Override
	public void onDataChanged(DataEventBuffer dataEvents)
	{
		test.setText("good");
		Log.e("test", "indatachanged");
		for (DataEvent event : dataEvents)
		{
			if (event.getType() == DataEvent.TYPE_CHANGED && event.getDataItem().getUri().getPath().startsWith("/plants/"))
			{
				DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
				List<DataMap> elementsDataMap = dataMapItem.getDataMap().getDataMapArrayList("/list/");
				Log.e("test", "indatachanged1");
				if (plantList == null || plantList.isEmpty())
				{
					Log.e("test", "indatachanged2");
					plantList = new ArrayList<>();

					for (DataMap dataMap : elementsDataMap)
					{
						plantList.add(getPlant(dataMap));
					}
				}

				pager.setAdapter(new ElementGridPagerAdapter(getFragmentManager(), plantList));
			}
		}
	}

	public Plant getPlant(DataMap plantDataMap)
	{
		return new Plant(
				plantDataMap.getString("name"),
				plantDataMap.getString("description"),
				plantDataMap.getFloat("temperature"),
				plantDataMap.getFloat("light"),
				plantDataMap.getFloat("humidity"));
	}

	/**
	 * Appellé à la réception d'un message envoyé depuis le smartphone
	 *
	 * @param messageEvent message reçu
	 */
	@Override
	public void onMessageReceived(MessageEvent messageEvent)
	{
		//traite le message reçu
		final String path = messageEvent.getPath();

		if (path.equals("bonjour"))
		{

			//récupère le contenu du message
			final String message = new String(messageEvent.getData());


		}
	}

	/**
	 * Envoie un message à au smartphone
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
}

package com.android.fishroom.bulbasaur.lib.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.RowSingleton;
import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.controller.handler.RowsResponseHandler;
import com.android.fishroom.bulbasaur.data.SerializableSparseArray;
import com.android.fishroom.bulbasaur.lib.manager.APIManager;
import com.android.fishroom.bulbasaur.lib.manager.ResponseManager;
import com.android.fishroom.bulbasaur.model.Rows;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by EXIA on 28/05/2015.
 */
public class dailyFragment extends Fragment implements Response<SerializableSparseArray<Rows>>
{
	private static final String DAILY_ROWS = "daily_rows";

	private boolean hasAxes = true;
	private boolean hasAxesNames = true;
	private boolean hasLines = true;
	private boolean hasPoints = true;
	private ValueShape shape = ValueShape.CIRCLE;
	private boolean isFilled = false;
	private boolean hasLabels = false;
	private boolean isCubic = false;
	private boolean hasLabelForSelected = false;
	private LineChartData data;

	private LineChartView chartView;

	protected int categorie;

	private boolean load = false;

	protected int numberOfPoints;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.fragment_temperature_vp, container, false);

		chartView = (LineChartView) view.findViewById(R.id.chart);

		chartView.setViewportCalculationEnabled(false);

		setCategorie();

		return view;
	}

	protected void setCategorie()
	{

	}

	private void resetViewport(int top)
	{
		final Viewport v = new Viewport(chartView.getMaximumViewport());

		v.bottom = 0;
		v.top = top;
		v.left = 0;
		v.right = numberOfPoints - 1;
		chartView.setMaximumViewport(v);
		chartView.setCurrentViewport(v);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		Log.e("test", "setRequest");
		setRequest();
	}

	protected void setRequest()
	{
		if (RowSingleton.getInstance().getDailyRows() == null)
		{
			ResponseManager.getInstance().attach(DAILY_ROWS, this);

			RowsResponseHandler response = new RowsResponseHandler();
			APIManager.getInstance().getDaily(response);
			ResponseManager.getInstance().addResponse(DAILY_ROWS, response, this);

			load = true;
		}
		else
		{
			generateData(RowSingleton.getInstance().getDailyRows());
		}
	}

	private void loadChart()
	{

	}

	private void generateData(SerializableSparseArray<Rows> rows)
	{
		numberOfPoints = rows.size();

		switch (categorie)
		{
			case 0:
				resetViewport(35);
				break;

			case 1:
				resetViewport(100);
				break;

			case 2:
				resetViewport(100);
				break;

			default:
				resetViewport(100);
				break;
		}

		List<Line> lines = new ArrayList<Line>();
		for (int i = 0; i < 1; ++i)
		{
			List<PointValue> values = new ArrayList<PointValue>();

			for (int j = 0; j < numberOfPoints; ++j)
			{
				switch (categorie)
				{
					case 0:
						values.add(new PointValue(j, rows.get(j).getValue().getTemp()));
						break;

					case 1:
						values.add(new PointValue(j, rows.get(j).getValue().getLight()));
						break;

					case 2:
						values.add(new PointValue(j, rows.get(j).getValue().getHumidity()));
						break;

					default:
						values.add(new PointValue(j, rows.get(j).getValue().getTemp()));
						break;
				}
			}

			Line line = new Line(values);

			line.setColor(getResources().getColor(R.color.colorPrimary));
			line.setShape(shape);
			line.setCubic(isCubic);
			line.setFilled(isFilled);
			line.setHasLabels(hasLabels);
			line.setHasLabelsOnlyForSelected(hasLabelForSelected);
			line.setHasLines(hasLines);
			line.setHasPoints(hasPoints);
			lines.add(line);
		}

		data = new LineChartData(lines);

		if (hasAxes)
		{
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames)
			{
				axisX.setName("Température");
				axisY.setName("Heure");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);

			data.setBaseValue(Float.NEGATIVE_INFINITY);
			chartView.setLineChartData(data);
		}
	}

	@Override
	public void handleResponse(SerializableSparseArray<Rows> rows, boolean more)
	{
		SerializableSparseArray<Rows> OrderedRows = new SerializableSparseArray<Rows>(1);
		OrderedRows.clear();

		boolean findMidnight = false;
		int newIndex = 0;

		for (int index = rows.size() - 1; index >= 0; index--)
		{
			if (!findMidnight)
			{
				Log.e("test", String.valueOf(rows.get(index).getKey()));
				Log.e("test", String.valueOf(rows.get(index).getKey().contains("T22")));
				if (rows.get(index).getKey().contains("T22"))
				{
					findMidnight = true;
					OrderedRows.put(newIndex, rows.get(index));
					newIndex++;
				}
			}
			else
			{
				OrderedRows.put(newIndex, rows.get(index));
				newIndex++;
			}
		}
		Log.e("testOrdered", String.valueOf(OrderedRows));
		RowSingleton.getInstance().setDailyRows(OrderedRows);
		generateData(OrderedRows);
	}

	@Override
	public void handleError()
	{

	}
}

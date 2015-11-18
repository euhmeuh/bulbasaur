package com.android.fishroom.bulbasaur.lib.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fishroom.bulbasaur.R;
import com.android.fishroom.bulbasaur.RowSingleton;
import com.android.fishroom.bulbasaur.controller.handler.MonthlyRowResponseHandler;
import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.controller.handler.WeeklyRowResponseHandler;
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

public class MonthlyFragment extends Fragment implements Response<SerializableSparseArray<Rows>>
{
	private static final String MONTHLY_ROWS = "monthly_rows";

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
		v.left = 1;
		v.right = 30;
		chartView.setMaximumViewport(v);
		chartView.setCurrentViewport(v);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		setRequest();
	}

	protected void setRequest()
	{
		if (RowSingleton.getInstance().getMonthlyRows() == null)
		{
			ResponseManager.getInstance().attach(MONTHLY_ROWS, this);

			MonthlyRowResponseHandler response = new MonthlyRowResponseHandler();
			APIManager.getInstance().getMonthly(response);
			ResponseManager.getInstance().addResponse(MONTHLY_ROWS, response, this);

			load = true;
		}
		else
		{
			generateData(RowSingleton.getInstance().getMonthlyRows());
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
				axisY.setName("Jour");
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
		RowSingleton.getInstance().setMonthlyRows(rows);
		generateData(rows);
	}

	@Override
	public void handleError()
	{

	}
}
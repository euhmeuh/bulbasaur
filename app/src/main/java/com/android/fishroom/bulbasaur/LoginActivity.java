package com.android.fishroom.bulbasaur;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.fishroom.bulbasaur.controller.handler.PlantsResponseHandler;
import com.android.fishroom.bulbasaur.controller.handler.Response;
import com.android.fishroom.bulbasaur.controller.handler.UserResponseHandler;
import com.android.fishroom.bulbasaur.lib.manager.APIManager;
import com.android.fishroom.bulbasaur.lib.manager.ResponseManager;
import com.android.fishroom.bulbasaur.model.Plant;
import com.android.fishroom.bulbasaur.model.User;

public class LoginActivity extends Activity implements Response<User>
{
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String PREFS_USER_ID = "user_id";
	private static final String USER_CONNECT = "user_connect";

	private ProgressDialog progressDialog;

	private EditText login;
	private EditText password;
	private Button validate;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		validate = (Button) findViewById(R.id.validate);
		login = (EditText) findViewById(R.id.login);
		password = (EditText) findViewById(R.id.password);

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Chargement...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setIndeterminate(true);

		ResponseManager.getInstance().attach(USER_CONNECT, this);


		validate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				sendRequest();
			}
		});
	}

	public void sendRequest()
	{
		progressDialog.show();

		UserResponseHandler response = new UserResponseHandler();
		APIManager.getInstance().UserConnect(response, login.getText().toString(), password.getText().toString());
		ResponseManager.getInstance().addResponse(USER_CONNECT, response, this);
	}

	@Override
	public void handleResponse(User data, boolean more)
	{
		if (!TextUtils.isEmpty(data.getId()))
		{
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString(PREFS_USER_ID, data.getId()).apply();

			Intent intent = new Intent(this, PlantListActivity.class);
			intent.putExtra(PlantListActivity.USER_ARG, data);
			startActivity(intent);
			finish();
		}
		else
		{
			Toast.makeText(this, "Erreur dans la combinaison login/mot de passe.", Toast.LENGTH_SHORT).show();
		}

		progressDialog.dismiss();
	}

	@Override
	public void handleError()
	{
		progressDialog.dismiss();

		Toast.makeText(this, "Erreur dans la combinaison login/mot de passe.", Toast.LENGTH_SHORT).show();
	}
}

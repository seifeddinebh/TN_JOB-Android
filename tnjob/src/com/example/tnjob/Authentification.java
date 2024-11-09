package com.example.tnjob;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Authentification extends Activity {

	String off;
	String idoffre;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentification);
		Bundle objetbunble = this.getIntent().getExtras(); 
		idoffre=objetbunble.getString("id");
		off=objetbunble.getString("offre");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.authentification, menu);
		return true;
	}
	
	public void actionatuth(View view)
	{
		InputStream is = null;
		
		List<Persist> list = new ArrayList<Persist>();
		list.clear();
			try {
		
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			EditText log=(EditText)findViewById(R.id.editText1);
			EditText mpt=(EditText)findViewById(R.id.EditText01);
			
			 nameValuePairs.add(new BasicNameValuePair("login",log.getText().toString()));
			 nameValuePairs.add(new BasicNameValuePair("mpt",mpt.getText().toString()));
		        
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2:80/android/auth.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.i("444444", "4444444");
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection" + e.toString());
		}

		String result = null;
		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = "0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			is.close();
			result = sb.toString();

		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}


		try {
			JSONArray jArray = new JSONArray(result);
			
 
		Toast.makeText(getApplicationContext(), "Authentification réussite", Toast.LENGTH_SHORT).show();
		JSONObject json_data = jArray.getJSONObject(0);
		
		if (json_data.getString("username").equals("candidat")) 
			{
			Intent intent = new Intent(Authentification.this,Detaille.class);
			Bundle objetbunble = new Bundle();
			objetbunble .putString("id",idoffre);
			objetbunble .putString("id_client",json_data.getString("id"));
			intent.putExtras(objetbunble);
			startActivity(intent);
				
			}
		else 
		{
			Intent intent = new Intent(Authentification.this,EspaceEntre.class);
			Bundle objetbunble = new Bundle();
			objetbunble .putString("id_client",json_data.getString("id"));
			intent.putExtras(objetbunble);
			startActivity(intent);
			
			
			
			
		
	
		
		}
			Log.i("55555", "5555");
		} catch (Exception e) {
			 Toast.makeText(getApplicationContext(), "Login ou mot de passe est incorrect", Toast.LENGTH_SHORT).show();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

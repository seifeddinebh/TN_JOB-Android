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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Detaille extends Activity {
	String id_client;
	String idoffre;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detaille);
		Bundle objetbunble = this.getIntent().getExtras(); 
		idoffre=objetbunble.getString("id");
		id_client=objetbunble.getString("id_client");
		InputStream is = null;
		
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			 nameValuePairs.add(new BasicNameValuePair("idoffre",idoffre));
		        
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2:80/android/detailleoffre.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

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
			JSONObject json_data = null;
		
		
				json_data = jArray.getJSONObject(0);
				
				TextView position=(TextView)findViewById(R.id.inf1);
				position.setText(json_data.getString("position"));
				TextView locution=(TextView)findViewById(R.id.inf2);
				locution.setText(json_data.getString("location"));
				TextView description=(TextView)findViewById(R.id.inf3);
			description.setText(json_data.getString("description"));
				
				
			

		} catch (Exception e) {
			Log.e("EE", e.getMessage());
		}
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detaille, menu);
		return true;
	}
	public void postuler(View view)
	{
		if(id_client==null)
		{
			Intent intent = new Intent(Detaille.this,Authentification.class);
			Bundle objetbunble = new Bundle();
			objetbunble .putString("id",idoffre);
			objetbunble .putString("offre","activdettail");
			intent.putExtras(objetbunble);
			startActivity(intent);
		}
		
		
		
		
		
		
		else 
		{
			InputStream is = null;
			
			try {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				 nameValuePairs.add(new BasicNameValuePair("idoffre",idoffre));
				 nameValuePairs.add(new BasicNameValuePair("id_client",id_client));
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://10.0.2.2:80/android/postuler.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();

			} catch (Exception e) {
				Log.e("log_tag", "Error in http connection" + e.toString());
			}
			
			String result = null;
			// convert response to string
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
				 String line = null;
			        line = reader.readLine();
	 is.close();
	 if(line.equals("1"))Toast.makeText(getApplicationContext(), "postulation réussite", Toast.LENGTH_SHORT).show();
	 else Toast.makeText(getApplicationContext(), "erreur", Toast.LENGTH_SHORT).show();	
	 Intent intent = new Intent(Detaille.this,Acceuil.class);
			Bundle objetbunble = new Bundle();
	
			objetbunble .putString("id_client",id_client);
			intent.putExtras(objetbunble);
			startActivity(intent);



			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}
			
			
			
			
			
			
			
		}
	}

}

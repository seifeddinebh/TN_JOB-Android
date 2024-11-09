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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EspaceEntre extends Activity {
	String id_client;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_espace_entre);
       Bundle objetbunble = this.getIntent().getExtras(); 
	id_client=objetbunble.getString("id_client");
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.espace_entre, menu);
		return true;
	}
	
public void actionatuth(View view)
	{
	InputStream is = null;
	
	try {
		EditText type=(EditText)findViewById(R.id.editText1);
		EditText pos=(EditText)findViewById(R.id.EditText01);
		EditText loc=(EditText)findViewById(R.id.EditText02);
		EditText desc=(EditText)findViewById(R.id.editText2);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		 nameValuePairs.add(new BasicNameValuePair("id_client",id_client));
		 nameValuePairs.add(new BasicNameValuePair("type",type.getText().toString()));
		 nameValuePairs.add(new BasicNameValuePair("pos",pos.getText().toString()));
		 nameValuePairs.add(new BasicNameValuePair("loc",loc.getText().toString()));
 nameValuePairs.add(new BasicNameValuePair("des",desc.getText().toString()));
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://10.0.2.2:80/android/insertion.php");
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
if(line.equals("1"))Toast.makeText(getApplicationContext(), "Insertion réussite", Toast.LENGTH_SHORT).show();
else Toast.makeText(getApplicationContext(), "erreur", Toast.LENGTH_SHORT).show();	
Intent intent = new Intent(EspaceEntre.this,Acceuil.class);
	Bundle objetbunble = new Bundle();

	objetbunble .putString("id_client",id_client);
	intent.putExtras(objetbunble);
	startActivity(intent);



	} catch (Exception e) {
		Log.e("log_tag", "Error converting result " + e.toString());
	}
	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

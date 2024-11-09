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

import android.util.Log;

public class Persist {
	private String id;
	private String category;
	private String user;
	private String type;
	private String position;
	private String location;
	private String description;
	private String how_to_apply;
	private String token;
	private String is_public;
	private String is_activated;
	private String email;
	private String expires_at;
	private String created_at;
	private String updated_at;
	private String idcat;
	private String nomcat;
	
	public String getNomcat() {
		return nomcat;
	}
	public void setNomcat(String nomcat) {
		this.nomcat = nomcat;
	}








	List<Persist> listoffre = new ArrayList<Persist>();
	List<Persist> listcat = new ArrayList<Persist>();
	List<Persist> listoffcat = new ArrayList<Persist>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdcat() {
		return idcat;
	}
	public void setIdcat(String idcat) {
		this.idcat = idcat;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHow_to_apply() {
		return how_to_apply;
	}
	public void setHow_to_apply(String how_to_apply) {
		this.how_to_apply = how_to_apply;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getIs_public() {
		return is_public;
	}
	public void setIs_public(String is_public) {
		this.is_public = is_public;
	}
	public String getIs_activated() {
		return is_activated;
	}
	public void setIs_activated(String is_activated) {
		this.is_activated = is_activated;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpires_at() {
		return expires_at;
	}
	public void setExpires_at(String expires_at) {
		this.expires_at = expires_at;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	public List<Persist> getListoffre() {
		listoffre.clear();
		listoffre=setoffre();
		return listoffre;
	}
	public List<Persist> getListcat() {
		listcat.clear();
		listcat=setcat();
		return listcat;
	}
	public List<Persist> getListoffcat(String idcat) {
		listoffcat.clear();
		listoffcat=setofcat(idcat);
		return listoffcat;
	}
	/* ********************************************/
	
	public List<Persist> setoffre() {
		

		InputStream is = null;
		
		List<Persist> list = new ArrayList<Persist>();
		list.clear();
 		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			
		        
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2:80/android/listoffre.php");
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
			JSONObject json_data = null;
		
			
			for (int i = 0; i < jArray.length(); i++) {
				Persist Persist = new Persist();
				json_data = jArray.getJSONObject(i);
				Persist.setId(json_data.getString("id"));
				Persist.setCategory(json_data.getString("category"));
				Persist.setUser(json_data.getString("user"));
				Persist.setType(json_data.getString("type"));
				Persist.setPosition(json_data.getString("position"));
				Persist.setLocation(json_data.getString("location"));
				Persist.setDescription(json_data.getString("description"));
				Persist.setHow_to_apply(json_data.getString("how_to_apply"));
				Persist.setToken(json_data.getString("token"));
				Persist.setIs_public(json_data.getString("is_public"));
				Persist.setIs_activated(json_data.getString("is_activated"));
				Persist.setEmail(json_data.getString("email"));
				Persist.setExpires_at(json_data.getString("expires_at"));
				Persist.setCreated_at(json_data.getString("created_at"));
				Persist.setUpdated_at(json_data.getString("updated_at"));
	            
				list.add(i, Persist);
				
			}
			Log.i("55555", "5555");
		} catch (Exception e) {
			Log.e("1111111", e.getMessage());
		}
		return list;
	}
	
	
	
	
	
	
	
	
public List<Persist> setcat() {
		

		InputStream is = null;
		
		List<Persist> list = new ArrayList<Persist>();
		list.clear();
 		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			
		        
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2:80/android/listcat.php");
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
			JSONObject json_data = null;
		
			
			for (int i = 0; i < jArray.length(); i++) {
				Persist Persist = new Persist();
				json_data = jArray.getJSONObject(i);
				Persist.setIdcat(json_data.getString("id"));
				Persist.setNomcat(json_data.getString("name"));
			
	            
				list.add(i, Persist);
				
			}
			Log.i("55555", "5555");
		} catch (Exception e) {
			Log.e("1111111", e.getMessage());
		}
		return list;
	}
	
	
	
	
	/**************************************************************/
public List<Persist> setofcat( String idcat) {
	

	InputStream is = null;
	
	List<Persist> list = new ArrayList<Persist>();
	list.clear();
		try {
	
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		 nameValuePairs.add(new BasicNameValuePair("idcat",idcat));
	        
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://10.0.2.2:80/android/listoffcat.php");
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
		JSONObject json_data = null;
	
		
		for (int i = 0; i < jArray.length(); i++) {
			Persist Persist = new Persist();
			json_data = jArray.getJSONObject(i);
			Persist.setId(json_data.getString("id"));
			Persist.setCategory(json_data.getString("category"));
			Persist.setUser(json_data.getString("user"));
			Persist.setType(json_data.getString("type"));
			Persist.setPosition(json_data.getString("position"));
			Persist.setLocation(json_data.getString("location"));
			Persist.setDescription(json_data.getString("description"));
			Persist.setHow_to_apply(json_data.getString("how_to_apply"));
			Persist.setToken(json_data.getString("token"));
			Persist.setIs_public(json_data.getString("is_public"));
			Persist.setIs_activated(json_data.getString("is_activated"));
			Persist.setEmail(json_data.getString("email"));
			Persist.setExpires_at(json_data.getString("expires_at"));
			Persist.setCreated_at(json_data.getString("created_at"));
			Persist.setUpdated_at(json_data.getString("updated_at"));
		
            
			list.add(i, Persist);
			
		}
		Log.i("55555", "5555");
	} catch (Exception e) {
		Log.e("1111111", e.getMessage());
	}
	return list;
}
	
	
	
	
	
	
	
	
	
	
	
	
}

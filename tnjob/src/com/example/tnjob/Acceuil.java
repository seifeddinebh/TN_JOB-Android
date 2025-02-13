package com.example.tnjob;





import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;









import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Acceuil extends Activity {
	ListView maListViewPerso;
	String id_client;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceuil);
	
	Persist u=new Persist();
	maListViewPerso=(ListView) findViewById(R.id.listviewperso);
	 ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
	
	 for(int i=0;i<u.getListoffre().size();i++){
			HashMap<String, Object> map = new HashMap<String, Object>();
		
			 map.put("id", u.getListoffre().get(i).getId());
			 map.put("category", u.getListoffre().get(i).getCategory());
			 map.put("user", u.getListoffre().get(i).getUser());
			 map.put("type", u.getListoffre().get(i).getType());
			 map.put("position", u.getListoffre().get(i).getPosition());
			 map.put("location", u.getListoffre().get(i).getLocation());
			 map.put("description", u.getListoffre().get(i).getDescription().substring(0, 30)+"...");
		
			 map.put("how_to_apply", u.getListoffre().get(i).getHow_to_apply());
			 map.put("token", u.getListoffre().get(i).getToken());
			 map.put("is_public", u.getListoffre().get(i).getIs_public());
			 map.put("is_activated", u.getListoffre().get(i).getIs_activated());
			 map.put("email", u.getListoffre().get(i).getEmail());
			 map.put("expires_at", u.getListoffre().get(i).getExpires_at());
			 map.put("created_at", u.getListoffre().get(i).getCreated_at());
			 map.put("updated_at", u.getListoffre().get(i).getUpdated_at());
			 
			 
			 
			 
			 try {
		           URL url = new URL("http://10.0.2.2:80/android/smart.png");
		           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		           connection.setDoInput(true);
		           connection.connect();
		           InputStream input = connection.getInputStream();
		           Bitmap myBitmap = BitmapFactory.decodeStream(input);
		            map.put("source", myBitmap);
		          
		         } catch (IOException e) {
		        	 Log.e("222222222222", e.getMessage());
		       }
		      /* map.put("img", String.valueOf(R.drawable.ic_launcher));*/
		       //enfin on ajoute cette hashMap dans la arrayList
		       listItem.add(map);
	
	 }
	 SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.afficheitemmm,
             new String[] {"source", "position","location","description"}, new int[] {R.id.imageView1, R.id.inf1,R.id.inf2,R.id.inf3});
      mSchedule.setViewBinder(new MyViewBinder());

	       //On attribut � notre listView l'adapter que l'on vient de cr�er
	       maListViewPerso.setAdapter(mSchedule);
	       maListViewPerso.setOnItemClickListener(new OnItemClickListener() {
				@Override
	       	@SuppressWarnings("unchecked")
	        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					//on r�cup�re la HashMap contenant les infos de notre item (titre, description, img)
	       		HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);

	       		//on cr�er une boite de dialogue
	       		//AlertDialog.Builder adb = new AlertDialog.Builder( Categ.this);
	       		//on attribut un titre � notre boite de dialogue
	       		//adb.setTitle("S�lection Item");
	       		//on ins�re un message � notre boite de dialogue, et ici on affiche le titre de l'item cliqu�
	       		//adb.setMessage("Votre choix : "+map.get("NOM_CAT"));
	       		//on indique que l'on veut le bouton ok � notre boite de dialogue
	       		//adb.setPositiveButton("Ok", null);
	       		//on affiche la boite de dialogue
	       		//adb.show();
					Intent intent = new Intent(Acceuil.this, Detaille.class);
					Bundle objetbunble = new Bundle();
					if (id_client!=null) 	objetbunble .putString("id_client",id_client);
					objetbunble .putString("id",map.get("id"));
					intent.putExtras(objetbunble);
					startActivity(intent);
	       	}
	        });
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acceuil, menu);
		return true;
	}
	
	public void onClickacc(View view)
	{
		Intent intent = new Intent(Acceuil.this, Acceuil.class);
		startActivity(intent);
	}
	public void onClickoffre(View view)
	{
		Intent intent = new Intent(Acceuil.this, Listoffre.class);
		startActivity(intent);
	}
	public void onClicauth(View view)
	{
		Intent intent = new Intent(Acceuil.this, Authentification.class);
		Bundle objetbunble = new Bundle();
		objetbunble .putString("offre","cc");
		intent.putExtras(objetbunble);
		startActivity(intent);
		
	}
	
	

}

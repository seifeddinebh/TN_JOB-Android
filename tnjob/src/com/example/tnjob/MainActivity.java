package com.example.tnjob;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	MediaPlayer player;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Thread timer=new Thread(){

			public void run(){

			try{
				
			sleep(2000);

			}catch(InterruptedException e){
				
			e.printStackTrace();

			}finally{
			Intent in=new Intent(getApplicationContext(),Acceuil.class);
			Log.i("33333","33333");
			startActivity(in);
			}

						
			}
				
				};

					timer.start();
				}




				@Override
				
			protected void onPause() {

				
				

					super.onPause();

					finish();
				
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

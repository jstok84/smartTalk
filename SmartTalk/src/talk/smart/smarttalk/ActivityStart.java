package talk.smart.smarttalk;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ActivityStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		Button buttonPrijava = (Button) findViewById(R.id.ButtonPrijava);
		
		 buttonPrijava.setOnClickListener(new View.OnClickListener() {
			 
	            public void onClick(View arg0) {
	                //Starting a new Intent
	                Intent nextScreen = new Intent(getApplicationContext(), prijavaUp_GpsNet.class);
	                startActivity(nextScreen);
	            }
	         });
		 
		 Button buttonIzhod=(Button) findViewById(R.id.ButtonIzhod);
		 buttonIzhod.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		    	ErrorMesage();
		    	
			}
		});
		 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}
	public void ErrorMesage() {
		// TODO Auto-generated method stub
    	AlertDialog.Builder sporocilo=new AlertDialog.Builder(this);
    	sporocilo.setMessage("Ali res želite zapreti aplikacijo?")
    	.setCancelable(false)
    	.setPositiveButton("YES", new DialogInterface.OnClickListener() {
	
    		public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,@SuppressWarnings("unused") final int id) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		})
		.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			public void onClick(final DialogInterface dialog, @SuppressWarnings("unused")final int id) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});;
        final AlertDialog alert = sporocilo.create();
        alert.show();
    }
}

package talk.smart.smarttalk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class prijavaUp_GpsNet extends FragmentActivity{
	
    private GPSTracker gps;
    private NETcheck net;
    private int stevec=0;//stevec in stevec1 nam omogoèata, da nam izmenièno prikazuje error za gps in wifi
    private int stevec1=0;
    
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prijava_gps_net);

		
		
		//gumb ki preveri gps in net ter nas preusmeri na zemljevid
		Button buttonNaZem = (Button) findViewById(R.id.ButtonNaZemljevid);
		 buttonNaZem.setOnClickListener(new View.OnClickListener() {
			 
	            public void onClick(View arg0) {
	                //Starting a new Intent
	            	
	            	gps = new GPSTracker(getApplicationContext());
	    			net = new NETcheck(getApplicationContext());
	    			//ce stevec=1 potem bo odprlo errorNet; =0 pa errorGps
	    			if(gps.isGPSEnabled)stevec=1;
	    			else if(net.getIsEnabled())stevec=0;
	    			
	    			if(stevec==0 && !gps.isGpsEnabled()){
	    		    	GPSErrorMesage();	  
	    		    	  stevec++;
	    		    	  
	    		    }else if(stevec==1 && !net.getIsEnabled()){
	    		    	 NETErrorMesage();
	    		    	//Toast.makeText(getApplicationContext(), "tle", Toast.LENGTH_SHORT).show();
	    		          stevec--;
	    		    }else if(gps.isGPSEnabled && net.getIsEnabled())   {  
		            	try{
		     	        	Intent nextScreen = new Intent(getApplicationContext(), MapActivity.class);
		     	        	startActivity(nextScreen);
		            	}catch(Exception e){
		            		Toast.makeText(getApplicationContext(), e.getStackTrace()+"", Toast.LENGTH_SHORT).show();
		            	}
	    		    }   
	            }
	         });
		 
	}
	
	public void GPSErrorMesage() {
		// TODO Auto-generated method stub
    	AlertDialog.Builder sporociloGPS=new AlertDialog.Builder(this);
    	sporociloGPS.setMessage("Vaša GPS pvezava je izkljuèena. Ali jo želite vkljuèiti sedaj?")
    	.setCancelable(false)
    	.setPositiveButton("YES", new DialogInterface.OnClickListener() {
	
    		public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,@SuppressWarnings("unused") final int id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			}
		})
		.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			public void onClick(final DialogInterface dialog, @SuppressWarnings("unused")final int id) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
        final AlertDialog alert = sporociloGPS.create();
        alert.show();
    }
	
	public void NETErrorMesage() {
		// TODO Auto-generated method stub
    	AlertDialog.Builder sporociloGPS=new AlertDialog.Builder(this);
    	sporociloGPS.setMessage("Vaša podatkovna povezava je izkljuèena. Ali jo želite vkljuèiti sedaj?")
    	.setCancelable(false)
    	.setPositiveButton("WIFI", new DialogInterface.OnClickListener() {
	
    		public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,@SuppressWarnings("unused") final int id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
			}
		})
		.setNeutralButton("3G", new DialogInterface.OnClickListener() {
	
    		public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,@SuppressWarnings("unused") final int id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS));
			}
		})
		.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			public void onClick(final DialogInterface dialog, @SuppressWarnings("unused")final int id) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});;
        final AlertDialog alert = sporociloGPS.create();
        alert.show();
    }
}

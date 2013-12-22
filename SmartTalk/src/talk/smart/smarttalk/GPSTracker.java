package talk.smart.smarttalk;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class GPSTracker extends Service implements LocationListener {

	private Context context;
	boolean isGPSEnabled=false;
	boolean canGetLocation=false;
	Location location;
	double latit;
	double longi;
	
	//min razdalja da dobimo nove GPS podatke
	private static final long min_dolzina=10;//10m
	//min cas za update
	private static final long min_cat_za_update=1000*60;//1min
	
	protected LocationManager locationManager;
	
	public GPSTracker(Context context){
		this.context=context;
		getLocation();
		
	}
	private Location getLocation() {
		// TODO Auto-generated method stub
		try{
			locationManager=(LocationManager) context.getSystemService(LOCATION_SERVICE);
			//preveri èe je uporabnik obkljukal gps
			isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if(isGPSEnabled){
				//Toast.makeText(context, "kul", Toast.LENGTH_SHORT).show();
				if(location==null){
					locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, min_cat_za_update, min_dolzina, this);
					if(locationManager!=null){
						location=locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
						if(location!=null){
							latit=location.getLatitude();
							longi=location.getLongitude();
						}					
					}
				}
				
			}else{
				//Toast.makeText(context, "err", Toast.LENGTH_SHORT).show();
				//GPSErrorMesage();
			}
		}catch(Exception e){
			Toast.makeText(getApplicationContext(), "napaka GPStracker", Toast.LENGTH_LONG).show();
		}
		return location;
	}
	public double getLatitude(){
		if(location!=null){
			return this.location.getLatitude();	
		}else
			return this.location.getLatitude();
	}
	public double getLongitude(){
		if(location!=null){
			return this.location.getLongitude();	
		}else
			return this.location.getLongitude();
	}
	public boolean canGetLocation(){
		return this.canGetLocation;
	}
	public boolean isGpsEnabled(){
		this.isGPSEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	    // Toast.makeText(getApplicationContext(), "gfd", Toast.LENGTH_SHORT).show();
		return this.isGPSEnabled;
		
	}
	private void GPSErrorMesage() {
		Toast.makeText(context, "jolo", Toast.LENGTH_SHORT).show();
		/*
		// TODO Auto-generated method stub
    	AlertDialog.Builder sporociloGPS=new AlertDialog.Builder(this);
    	sporociloGPS.setMessage("Vasa GPS naprava je izkljucena. Želite jo vkljuciti sedaj?")
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
        alert.show();*/
    }
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

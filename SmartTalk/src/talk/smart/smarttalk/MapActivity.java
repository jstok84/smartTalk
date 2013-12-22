package talk.smart.smarttalk;

import java.text.BreakIterator;
import java.util.ArrayList;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationStatusCodes;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter.LengthFilter;
import android.util.AndroidException;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MapActivity extends FragmentActivity{
	public GoogleMap map;
	private Toast toast;
	
	   private FragmentManager fm;
	   private MapFragment mMapFragment;
	   private Uporabnik uporabnikTegaTelefona;
	   private GPSTracker gps;
	   private ArrayList<Uporabnik> uporabniki;
	   private ArrayList<Uporabnik> moski;
	   private ArrayList<Uporabnik> zenske;
	   private ArrayList<Uporabnik> samski;
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_map);  
	        
	       gps = new GPSTracker(getApplicationContext());
		     
	        try{
	        	  map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
		      
		    	 //uporabnike tukaj preberemo iz baze podatkov
		       uporabnikTegaTelefona=new Uporabnik("Sašo",2,"Moski","samski");
		       Uporabnik up1 = new Uporabnik("Janez",new LatLng(46.049814,14.488176),1,"Moski","v razmerju");
		       Uporabnik up2 = new Uporabnik("Miha",new LatLng(46.049776,14.48779),1,"Moski","samski");
		       Uporabnik up3 = new Uporabnik("Gregor",new LatLng(46.045297,14.490942),1,"Moski","porocen");
		       Uporabnik up4 = new Uporabnik("Liza",new LatLng(46.049855,14.487552),1,"Zenski","samski");
		        
		        uporabniki= new ArrayList<Uporabnik>();
		        uporabniki.add(up1);
		        uporabniki.add(up2);
		        uporabniki.add(up3);
		        uporabniki.add(up4);
		        
		        zenske= new ArrayList<Uporabnik>();
		        zenske.add(up4);
		        
		        moski= new ArrayList<Uporabnik>();
		        moski.add(up1);
		        moski.add(up2);
		        moski.add(up3);
		        
		        samski= new ArrayList<Uporabnik>();
		        samski.add(up2);
		        samski.add(up4);	        
		        
	        	//uporabnikTegaTelefona.setLokacija(new LatLng(gps.getLatitude(), gps.getLongitude()));
	        	
	        	uporabnikTegaTelefona.setLokacija(new LatLng(46.049828,14.487132));
		        
	        	//uporabniki.add(uporabnikTegaTelefona);
		        //moski.add(uporabnikTegaTelefona);
		        //samski.add(uporabnikTegaTelefona);
	        	
		        nastaviPolozajUporabnikaTegaTelefona(uporabnikTegaTelefona,1);
	        	nastaviPolozajeUporabnikov(uporabniki,1);
		        map.setMyLocationEnabled(false);
		        
	        }catch(NullPointerException e){
	        	Toast toast = Toast.makeText(getApplicationContext(), "NULL vrednost", Toast.LENGTH_LONG);
	        	toast.show();     	
	        }
	    }
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_map, menu);
			return true;
		}
		public boolean onOptionsItemSelected(MenuItem item){
			
			switch(item.getItemId()){
				case R.id.ZacetnaLokacija:
					 map.moveCamera(CameraUpdateFactory.newLatLngZoom(uporabnikTegaTelefona.getLokacija(), 18));
						Toast.makeText(getApplicationContext(), "Nazaj na vašo lokacijo.", Toast.LENGTH_SHORT).show();
			    		return true;
				case R.id.Vsi:
					map.clear();
					nastaviPolozajeUporabnikov(uporabniki, 2);
					nastaviPolozajUporabnikaTegaTelefona(uporabnikTegaTelefona,2);
					Toast.makeText(getApplicationContext(), "Prikazani vsi uporabniki.", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.Samski:
					map.clear();
					nastaviPolozajeUporabnikov(samski, 2);
					nastaviPolozajUporabnikaTegaTelefona(uporabnikTegaTelefona,2);
					Toast.makeText(getApplicationContext(), "Prikazani vsi samski uporabniki.", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.zenske:
					map.clear();
					nastaviPolozajeUporabnikov(zenske, 2);
					nastaviPolozajUporabnikaTegaTelefona(uporabnikTegaTelefona,2);
					Toast.makeText(getApplicationContext(), "Prikazane vse zenske.", Toast.LENGTH_SHORT).show();
					return true;
				case R.id.moski:
					map.clear();
					nastaviPolozajeUporabnikov(moski, 2);
					nastaviPolozajUporabnikaTegaTelefona(uporabnikTegaTelefona,2);
					Toast.makeText(getApplicationContext(), "Prikazani vsi moški.", Toast.LENGTH_SHORT).show();
			}
			return super.onOptionsItemSelected(item);
		}
		private void nastaviPolozajeUporabnikov(ArrayList<Uporabnik> uporabniki, int id) {
			// TODO Auto-generated method stub
	    	for(Uporabnik u : uporabniki){
	    		if(id==1){//vrednosti 1 in 2- èe je 1pomeni da marker nima animacije, èene pa jo ima
			        map.addMarker(new MarkerOptions()
			        .title(u.getIme())
			         //.snippet("The most populous city in Australia.")
			        .position(u.getLokacija()));
	    		}else{
			        map.addMarker(new MarkerOptions()
			        .title(u.getIme())
			         //.snippet("The most populous city in Australia.")
			        .anchor(0.0f, 1.0f)
			        .position(u.getLokacija()));
			    }    		
	    	}	
		}
		private void nastaviPolozajUporabnikaTegaTelefona(Uporabnik uporabnik, int id){
			if(id==1){//vrednosti 1 in 2- èe je 1pomeni da marker nima animacije, èene pa jo ima
	        	map.addMarker(new MarkerOptions()
		        .title(uporabnikTegaTelefona.getIme())
		         //.snippet("The most populous city in Australia.")
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
		        .position(uporabnikTegaTelefona.getLokacija()));
		       // map.moveCamera(CameraUpdateFactory.newLatLngZoom(uporabnikTegaTelefona.getLokacija(), 10));	
		       // map.animateCamera(CameraUpdateFactory.zoomTo(18), 2500, null);
	        	//animacija kamere
	        	CameraPosition cameraAnimacija = new CameraPosition.Builder()
	            .target(uporabnikTegaTelefona.getLokacija())
	            .zoom(18)                   
	            .bearing(60)               
	            .tilt(30)                   
	            .build();                   
	        	map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAnimacija));
			
			}else{
	        	map.addMarker(new MarkerOptions()
		        .title(uporabnikTegaTelefona.getIme())
		         //.snippet("The most populous city in Australia.")
				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
		       	.anchor(0.0f, 1.0f)
				.position(uporabnikTegaTelefona.getLokacija()));
		        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(uporabnikTegaTelefona.getLokacija(), 18));									                   
			}

		}
		private void GPSErrorMesage() {
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
	        alert.show();
	    }
}
package talk.smart.smarttalk;

import android.R.string;

import com.google.android.gms.maps.model.LatLng;

public class Uporabnik {
	private String ime;
	private LatLng lokacija;
	private int lastnik;
	private String stan;
	private String spol;
	
	public Uporabnik(String ime, LatLng lokacija, int lastnik,String stan, String spol){
		this.ime=ime;
		this.lokacija=lokacija;
		this.lastnik=lastnik;
		this.spol=spol;
		this.stan=stan;
	}
	public Uporabnik(String ime, int lastnik,String stan, String spol){
		this.ime=ime;
		this.lastnik=lastnik;
		this.spol=spol;
		this.stan=stan;
	}
	public String getIme(){
		return this.ime;
	}
	public LatLng getLokacija(){
		return this.lokacija;
	}
	public int getLastnik(){
		return this.lastnik;
	}
	public void setLokacija(LatLng lokacija){
		this.lokacija=lokacija;
	}
}

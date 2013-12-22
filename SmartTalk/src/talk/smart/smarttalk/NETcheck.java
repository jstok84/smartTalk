package talk.smart.smarttalk;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;

public class NETcheck extends FragmentActivity{
	
	private Context con;
	private String connectionType="";
	protected static boolean isConnected=false;
	
	
	public NETcheck(Context con){
		this.con=con;
		this.connectionType=getNetStatus(con);
	}
	public static String getNetStatus(Context context){
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE );
		
		NetworkInfo info= cm.getActiveNetworkInfo();
		if(info!=null){
			if(info.getType()==ConnectivityManager.TYPE_WIFI){
				isConnected=true;
				return "TYPE_WIFI";
			}if(info.getType()==ConnectivityManager.TYPE_MOBILE){
				isConnected=true;
				return "TYPE_MOBILE";
			}
		}else{
			isConnected=false;
			return "not_connected";
		}
		return "3";
	}
	public String getConnectionType(){
		return this.connectionType;	
	}
	public boolean getIsEnabled(){
		return this.isConnected;
	}
	
}

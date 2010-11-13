package com.lehighmobile;

import java.util.*;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class lehighmobile extends MapActivity {
	static final int DIALOG_BUILDING_LIST = 0;
	/*currently lookup holds LL, then PA*/
	static final String [] lookup = {"40.607425, -75.374926", "40.607857,-75.378995"};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_BUILDING_LIST);
				
			}
		});
	}

	protected Dialog onCreateDialog(int id){
	Dialog d = null;
	/*HashMap <String, String>hm = new HashMap();
	hm.put("LL", "40.607425, -75.374926");
	hm.put("Packard   PA", "40.607857,-75.378995");*/
	switch(id){
	case DIALOG_BUILDING_LIST:
		final CharSequence[] items = {"Lewis Lab	LL", "Packard	PA"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Destination");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				String coordinates = lookup[item];
				
				/*variable coordinates will hold the coordinates as a string */
				if (item < 2){
					setContentView(R.layout.map);
					MapView mapView;
				
					mapView = (MapView)findViewById(R.id.mapview);
					mapView.setBuiltInZoomControls(true);
				}
				else{
					/*This means somthing went wrong, or item is not what I think it is*/
				}
			}
		});
		AlertDialog alert = builder.create();
		d = alert;
		break;
	}
	return d;

}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
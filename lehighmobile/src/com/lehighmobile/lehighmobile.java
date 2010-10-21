package com.lehighmobile;

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
	switch(id){
	case DIALOG_BUILDING_LIST:
		final CharSequence[] items = {"Lewis Lab", "Packard"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Destination");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				 setContentView(R.layout.map);
				 MapView mapView;
				
				 mapView = (MapView)findViewById(R.id.mapview);
				 mapView.setBuiltInZoomControls(true);
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
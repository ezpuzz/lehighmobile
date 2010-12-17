package com.lehighmobile;

import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class lehighmobile extends Activity {
	static final int DIALOG_SHOW_BUILDING = 0;
	static final int DIALOG_GET_DIRECTIONS = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						showDialog(DIALOG_SHOW_BUILDING);

					}
				});

		findViewById(R.id.button2).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						showDialog(DIALOG_GET_DIRECTIONS);

					}
				});
		
		findViewById(R.id.button3).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						Intent showMap = new Intent (lehighmobile.this, ShowMap.class);
						startActivity(showMap);
					}
				});
	}

	protected Dialog onCreateDialog(int id) {

		Arrays.sort(BuildingData.campusBuildings);
		CharSequence[] items = new CharSequence[BuildingData.campusBuildings.length];
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			items[q] = BuildingData.campusBuildings[q].name + " "
					+ BuildingData.campusBuildings[q].abbr;
		}

		Dialog d = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch (id) {
		case DIALOG_SHOW_BUILDING:
			builder.setTitle("Choose Building");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					ShowBuilding.currBuilding = BuildingData.campusBuildings[item];
					Intent showB = new Intent(lehighmobile.this,
							ShowBuilding.class);
					startActivity(showB);
				}
			});
			d = builder.create();
			break;
		case DIALOG_GET_DIRECTIONS:
			builder.setTitle("Choose Destination");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					ShowDirections.currBuilding = BuildingData.campusBuildings[item];
					Intent showD = new Intent(lehighmobile.this,
							ShowDirections.class);
					startActivity(showD);
				}
			});
			d = builder.create();
			break;
		}
		return d;

	}

}
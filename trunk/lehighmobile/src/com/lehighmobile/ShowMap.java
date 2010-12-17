package com.lehighmobile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class ShowMap extends MapActivity {

	private MyLocationOverlay myLocation;
	private MapView mapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		initMap();
		mapView.setClickable(true);
		mapView.setEnabled(true);

		mapView.displayZoomControls(true);

		createAndShowMyItemizedOverlay();
	}

	@Override
	protected void onResume() {
		/*
		 * onResume is is always called after onStart, even if the app hasn't
		 * been paused
		 */
		myLocation.enableMyLocation();
		super.onResume();
	}

	@Override
	protected void onPause() {
		/* GPS, as it turns out, consumes battery like crazy */
		myLocation.disableMyLocation();
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void initMap() {
		mapView = (MapView) findViewById(R.id.mapview);
		myLocation = new MyLocationOverlay(this, mapView);
		mapView.setBuiltInZoomControls(true);
		mapView.getController().setZoom(16);
		mapView.getOverlays().add(myLocation);
		myLocation.enableMyLocation();
		myLocation.enableCompass();
		myLocation.runOnFirstFix(new Runnable() {
			public void run() {
				// move to location
				mapView.getController().animateTo(myLocation.getMyLocation());
				mapView.getController().setZoom(17);
			}
		});
	}

	/**
	 * This method will be called whenever a change of the current position is
	 * submitted via the GPS.
	 * 
	 * @param newLocation
	 */
	protected void createAndShowMyItemizedOverlay() {
		List<Overlay> overlays = mapView.getOverlays();

		// first remove old overlay
		if (overlays.size() > 0) {
			for (Iterator iterator = overlays.iterator(); iterator.hasNext();) {
				iterator.next();
				iterator.remove();
			}
		}

		ArrayList<Drawable> BuildingIcons = new ArrayList<Drawable>();
		BuildingIcons.add(getResources().getDrawable(R.drawable.academic));
		BuildingIcons.add(getResources().getDrawable(R.drawable.fraternity));
		BuildingIcons.add(getResources().getDrawable(R.drawable.library));
		BuildingIcons.add(getResources().getDrawable(R.drawable.other));
		BuildingIcons.add(getResources().getDrawable(R.drawable.residential));
		BuildingIcons.add(getResources().getDrawable(R.drawable.restaurant));
		BuildingIcons.add(getResources().getDrawable(R.drawable.service));
		
		String[] buildingTypes = {"Academic"};

		// initialize icon
		for (Drawable drawable : BuildingIcons) {
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		}

		// academic building icon
		LehighItemizedOverlay academicOverlay = new LehighItemizedOverlay(
				BuildingIcons.get(0));
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if (BuildingData.campusBuildings[q].type == "Academic")
				academicOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(academicOverlay);

		// academic building icon
		LehighItemizedOverlay fratOverlay = new LehighItemizedOverlay(BuildingIcons.get(1));
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if (BuildingData.campusBuildings[q].type == "Fraternity")
				fratOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(fratOverlay);
		
		// academic building icon
		LehighItemizedOverlay libraryOverlay = new LehighItemizedOverlay(BuildingIcons.get(2));
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if (BuildingData.campusBuildings[q].type == "Library")
				libraryOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(libraryOverlay);
		
		// academic building icon
		LehighItemizedOverlay otherOverlay = new LehighItemizedOverlay(BuildingIcons.get(3));
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if (BuildingData.campusBuildings[q].type == "Other")
				otherOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(otherOverlay);
		
		// academic building icon
		LehighItemizedOverlay residentialOverlay = new LehighItemizedOverlay(BuildingIcons.get(4));
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if (BuildingData.campusBuildings[q].type == "Residential")
				residentialOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(residentialOverlay);
		
		// academic building icon
		LehighItemizedOverlay restOverlay = new LehighItemizedOverlay(BuildingIcons.get(5));
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if (BuildingData.campusBuildings[q].type == "Restaurant")
				restOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(restOverlay);
		
		// academic building icon
		LehighItemizedOverlay serviceOverlay = new LehighItemizedOverlay(BuildingIcons.get(6));
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if (BuildingData.campusBuildings[q].type == "Service")
				serviceOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(serviceOverlay);

		// user location overlay
		overlays.add(myLocation);

		// redraw map
		mapView.postInvalidate();
	}

}

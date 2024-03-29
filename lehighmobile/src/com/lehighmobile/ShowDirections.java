package com.lehighmobile;

import java.util.Iterator;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class ShowDirections extends MapActivity {

	private MyLocationOverlay myLocation;
	private MapView mapView;
	static public Building currBuilding = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		initMap();
        mapView.setClickable(true);
        mapView.setEnabled(true);
     
        mapView.displayZoomControls(true);
        
        createAndShowMyItemizedOverlay();
		mapView.getController().setCenter(currBuilding.getCoordinates());
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

		// initialize icon
		Drawable icon = getResources().getDrawable(R.drawable.pointer);
		icon.setBounds(0, 0, icon.getIntrinsicWidth(),
				icon.getIntrinsicHeight());

		// target building icon
		LehighItemizedOverlay targetOverlay = new LehighItemizedOverlay(icon);
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			if(BuildingData.campusBuildings[q].name == currBuilding.name)
			targetOverlay.addItem(BuildingData.campusBuildings[q]);
		}
		overlays.add(targetOverlay);

		// user location overlay
		overlays.add(myLocation);
		
		// redraw map
		mapView.postInvalidate();
	}

}

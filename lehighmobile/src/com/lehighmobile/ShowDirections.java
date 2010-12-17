package com.lehighmobile;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class ShowDirections extends MapActivity {

	private MyLocationOverlay myLocation;
	private MapView mapView;
	static public Building currBuilding = null;
	private LocationManager locManager;
	private LocationListener locListener;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		initMap();
		initLocationManager();
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
		mapView.getController().setZoom(18);
		mapView.getOverlays().add(myLocation);
	}
	
	private void initLocationManager() {
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 
		locListener = new LocationListener() {
 
			public void onLocationChanged(Location newLocation) {
				createAndShowMyItemizedOverlay(newLocation);
			}
 
			public void onProviderDisabled(String arg0) {
			}
 
			public void onProviderEnabled(String arg0) {
			}
 
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			}
		};
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				locListener);
 
	}
 
	/**
	 * This method will be called whenever a change of the current position
	 * is submitted via the GPS.
	 * @param newLocation
	 */
	protected void createAndShowMyItemizedOverlay(Location newLocation) {
		List<Overlay> overlays = mapView.getOverlays();
 
		// first remove old overlay
		if (overlays.size() > 0) {
			for (Iterator iterator = overlays.iterator(); iterator.hasNext();) {
				iterator.next();
				iterator.remove();
			}
		}
 
		// transform the location to a geopoint
		GeoPoint geopoint = new GeoPoint(
				(int) (newLocation.getLatitude() * 1E6), (int) (newLocation
						.getLongitude() * 1E6));
 
		// initialize icon
		Drawable icon = getResources().getDrawable(R.drawable.pointer);
		
		icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon
				.getIntrinsicHeight());
 
		// create my overlay and show it
		LehighItemizedOverlay overlay = new LehighItemizedOverlay(icon);
		for (int q = 0; q < BuildingData.campusBuildings.length; q++) {
			overlay.addItem(BuildingData.campusBuildings[q]);
		}
		mapView.getOverlays().add(overlay);
 
		// move to location
		//mapView.getController().animateTo(geopoint);
 
		// redraw map
		mapView.postInvalidate();
	}

}

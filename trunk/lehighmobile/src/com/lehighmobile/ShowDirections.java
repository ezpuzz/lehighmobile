package com.lehighmobile;

import android.os.Bundle;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class ShowDirections extends MapActivity {

	private MyLocationOverlay myLocation;
	private MapView mapView;
	static public Building currBuilding = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		initMap();
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

		myLocation.runOnFirstFix(new Runnable() {

			public void run() {
				try {					
					mapView.postInvalidate();
				} catch (Exception e) {
				}
			}
		});
	}

}

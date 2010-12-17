package com.lehighmobile;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import java.util.*;

public class LehighItemizedOverlay extends ItemizedOverlay<Building> {
	
	private Drawable marker;
	private Context mContext;

	private ArrayList<Building> mOverlays = new ArrayList<Building>();
	
	public LehighItemizedOverlay(Drawable defaultMarker) {
		super(defaultMarker);
		marker = defaultMarker;
	}

	@Override
	protected Building createItem(int arg0) {
		return mOverlays.get(arg0);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add((Building) overlay);
	    populate();
	}
	
	public void addItem(OverlayItem item){
		mOverlays.add((Building) item);
		populate();
	}
	
	public LehighItemizedOverlay(Drawable defaultMarker, Context context) {
		  super(defaultMarker);
		  mContext = context;
		}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		boundCenterBottom(marker);
	}

	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}

}

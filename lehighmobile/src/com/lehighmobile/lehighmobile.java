package com.lehighmobile;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class lehighmobile extends MapActivity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		setContentView(R.layout.map);
        		LinearLayout linearLayout;
                MapView mapView;
                
                mapView = (MapView)findViewById(R.id.mapview);
                mapView.setBuiltInZoomControls(true);
        	}
        });
        
        
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    
}
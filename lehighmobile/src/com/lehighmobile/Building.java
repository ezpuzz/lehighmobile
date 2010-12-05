package com.lehighmobile;

import com.google.android.maps.GeoPoint;

public class Building {
	public String name;
	private GeoPoint coordinates;
	
	Building (String name, GeoPoint coordinates)
	{
		this.name = name;
		this.coordinates = coordinates;
	}
	
	GeoPoint getCoordinates ()
	{
		return coordinates;
	}
}

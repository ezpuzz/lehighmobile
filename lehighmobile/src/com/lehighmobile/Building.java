package com.lehighmobile;

import com.google.android.maps.GeoPoint;

public class Building {
	public String name;
	public String abbr = "";
	public String type = "";
	private GeoPoint coordinates;
	
	
	Building (String name, GeoPoint coordinates)
	{
		this.name = name;
		this.coordinates = coordinates;
	}
	
	Building (String name, GeoPoint coordinates, String abbr, String type)
	{
		this.name = name;
		this.coordinates = coordinates;
		this.abbr = abbr;
		this.type = type;
	}
	
	Building (String name, GeoPoint coordinates, String type)
	{
		this.name = name;
		this.coordinates = coordinates;
		this.type = type;
	}
	
	GeoPoint getCoordinates ()
	{
		return coordinates;
	}
}

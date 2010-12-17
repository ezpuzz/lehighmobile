package com.lehighmobile;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class Building extends OverlayItem implements Comparable<Building> {
	public String name;
	public String abbr = "";
	public String type = "";
	private GeoPoint coordinates;
	
	
	Building (String name, GeoPoint coordinates)
	{
		super(coordinates, name, name);
		this.name = name;
		this.coordinates = coordinates;
	}
	
	Building (String name, GeoPoint coordinates, String abbr, String type)
	{
		super(coordinates, name, type);
		this.name = name;
		this.coordinates = coordinates;
		this.abbr = abbr;
		this.type = type;
	}
	
	Building (String name, GeoPoint coordinates, String type)
	{
		super(coordinates, name, type);
		this.name = name;
		this.coordinates = coordinates;
		this.type = type;
	}
	
	GeoPoint getCoordinates ()
	{
		return coordinates;
	}

	public int compareTo(Building other) {
		return this.name.compareTo(other.name);
	}
}

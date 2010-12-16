package com.lehighmobile;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class lehighmobile extends MapActivity implements LocationListener {
	static final int DIALOG_BUILDING_LIST = 0;
	/* currently lookup holds LL, then PA */
	static final Building[] campusBuildings = { 
		new Building("Taylor", new GeoPoint(	40604651	,	-75378474	),"Residential"),
		new Building("Dravo", new GeoPoint(	40604578	,	-75374966	),"Residential"),
		new Building("Drinker", new GeoPoint(	40604651	,	-75375727	),"Residential"),
		new Building("M&M", new GeoPoint(	40604789	,	-75377777	),"Residential"),
		new Building("Richards", new GeoPoint(	40605237	,	-75374805	),"Residential"),
		new Building("Delta Gamma", new GeoPoint(	40604390	,	-75379450	),"Fraternity"),
		new Building("Delta Upsilon", new GeoPoint(	40604545	,	-75380491	),"Fraternity"),
		new Building("Kappa Alpha", new GeoPoint(	40604081	,	-75381339	),"Fraternity"),
		new Building("Chi Psi", new GeoPoint(	40603869	,	-75380341	),"Fraternity"),
		new Building("Pi Beta Phi", new GeoPoint(	40604016	,	-75378850	),"Fraternity"),
		new Building("Umoja House", new GeoPoint(	40604154	,	-75377637	),"Residential"),
		new Building("Gamma Phi Beta", new GeoPoint(	40604276	,	-75377219	),"Fraternity"),
		new Building("Alpha Omicron Pi", new GeoPoint(	40603551	,	-75376468	),"Fraternity"),
		new Building("Sigma Chi", new GeoPoint(	40603576	,	-75378345	),"Fraternity"),
		new Building("Delta Tau Delta", new GeoPoint(	40603225	,	-75380630	),"Fraternity"),
		new Building("Delta Phi", new GeoPoint(	40603543	,	-75381339	),"Fraternity"),
		new Building("Kappa Sigma", new GeoPoint(	40602843	,	-75382658	),"Fraternity"),
		new Building("Phi Kappa Theta", new GeoPoint(	40602696	,	-75381918	),"Fraternity"),
		new Building("Sigma Phi Epsilon", new GeoPoint(	40602663	,	-75381489	),"Fraternity"),
		new Building("Phi Sigma Kappa", new GeoPoint(	40602851	,	-75380212	),"Fraternity"),
		new Building("Chi Phi", new GeoPoint(	40602729	,	-75379515	),"Fraternity"),
		new Building("Kappa Alpha Theta", new GeoPoint(	40602696	,	-75378871	),"Fraternity"),
		new Building("Alpha Gamma Delta", new GeoPoint(	40602509	,	-75376650	),"Fraternity"),
		new Building("Alpha Tau Omega", new GeoPoint(	40602777	,	-75375416	),"Fraternity"),
		new Building("Alpha Phi", new GeoPoint(	40601947	,	-75376232	),"Fraternity"),
		new Building("Lambda Chi Alpha", new GeoPoint(	40601947	,	-75376940	),"Fraternity"),
		new Building("Theta Xi", new GeoPoint(	40602109	,	-75378045	),"Fraternity"),
		new Building("Alpha Chi Omega", new GeoPoint(	40601775	,	-75379000	),"Fraternity"),
		new Building("Hilside House", new GeoPoint(	40601107	,	-75380566	),"Residential"),
		new Building("Sayre", new GeoPoint(	40601979	,	-75381027	),"Residential"),
		new Building("Theta Chi", new GeoPoint(	40602248	,	-75380427	),"Fraternity"),
		new Building("Psi Upsilon", new GeoPoint(	40605164	,	-75381414	),"Fraternity"),
		new Building("Psi Upsilon", new GeoPoint(	40605156	,	-75381387	),"Fraternity"),
		new Building("Development Annex", new GeoPoint(	40605824	,	-75381172	),"Service"),
		new Building("Academic Outreach", new GeoPoint(	40605966	,	-75381156	),"Service"),
		new Building("Risk Management", new GeoPoint(	40606211	,	-75381167	),"Service"),
		new Building("Warren Square D", new GeoPoint(	40606203	,	-75381870	),"Residential"),
		new Building("Jewish Student Center", new GeoPoint(	40606288	,	-75381601	),"Service"),
		new Building("Warren Square A", new GeoPoint(	40606683	,	-75381821	),"Residential"),
		new Building("Warren Square B", new GeoPoint(	40606785	,	-75381505	),"Residential"),
		new Building("Warren Square C", new GeoPoint(	40606830	,	-75381194	),"Residential"),
		new Building("Trembley Park", new GeoPoint(	40605429	,	-75379386	),"Residential"),
		new Building("Sayre Bldg", new GeoPoint(	40606203	,	-75380040	),"Residential"),
		new Building("Jazzma's Café", new GeoPoint(	40606813	,	-75380749	),"Restaurant"),
		new Building("Alumni Memorial Bldg Office of Admissions", new GeoPoint(	40606691	,	-75380148	),"Service"),
		new Building("President's House", new GeoPoint(	40606480	,	-75379305	),"	Other	"),
		new Building("Lehigh University Campus Police", new GeoPoint(	40605380	,	-75377975	),"Service"),
		new Building("University Center", new GeoPoint(	40606056	,	-75378276	),"Service"),
		new Building("Drown Hall", new GeoPoint(	40605698	,	-75377144	),"DR", "Academic"),
		new Building("Coppee Hall", new GeoPoint(	40605966	,	-75376543	),"CO", "Academic"),
		new Building("Linderman Library", new GeoPoint(	40606679	,	-75376934	),"Library"),
		new Building("Global Village", new GeoPoint(	40607105	,	-75378375	),"Other"),
		new Building("Lamberton hall", new GeoPoint(	40605547	,	-75376312	),"Service"),
		new Building("Coxe Hall", new GeoPoint(	40606048	,	-75375808	),"CX", "Academic"),
		new Building("Williams Hall", new GeoPoint(	40606671	,	-75375515	),"Residential"),
		new Building("Access Control/Locksmith", new GeoPoint(	40605584	,	-75375454	),"Service"),
		new Building("Price Hall", new GeoPoint(	40605934	,	-75375051	),"PR", "Academic"),
		new Building("Grace Hall/Ulrich Student Center", new GeoPoint(	40606272	,	-75374504	),"Service"),
		new Building("Newman Center", new GeoPoint(	40606679	,	-75374290	),"Service"),
		new Building("Rathbone Hall", new GeoPoint(	40606699	,	-75373008	),"Restaurant"),
		new Building("Taylor Gymnasium", new GeoPoint(	40607274	,	-75374075	),"Other"),
		new Building("Thornburg House", new GeoPoint(	40606190	,	-75373689	),"Residential"),
		new Building("Smiley House", new GeoPoint(	40606190	,	-75373287	),"Residential"),
		new Building("McConn House", new GeoPoint(	40606142	,	-75373088	),"Residential"),
		new Building("Leavitt House", new GeoPoint(	40606105	,	-75372825	),"Residential"),
		new Building("Centennial 1 Commons", new GeoPoint(	40606296	,	-75372761	),"Residential"),
		new Building("Emery House", new GeoPoint(	40606333	,	-75372455	),"Residential"),
		new Building("Centennail/Congdon", new GeoPoint(	40606349	,	-75372198	),"Residential"),
		new Building("Carothers House", new GeoPoint(	40607086	,	-75372155	),"Residential"),
		new Building("Beardslee House", new GeoPoint(	40606928	,	-75371881	),"Residential"),
		new Building("Williams House", new GeoPoint(	40607164	,	-75371624	),"Residential"),
		new Building("Stoughton House", new GeoPoint(	40607314	,	-75371870	),"Residential"),
		new Building("Stevens House", new GeoPoint(	40607351	,	-75372391	),"Residential"),
		new Building("Palmer House", new GeoPoint(	40607192	,	-75372584	),"Residential"),
		new Building("Maginnes Hall", new GeoPoint(	40608919	,	-75378925	),"MG", "Academic"),
		new Building("STEPS", new GeoPoint(	40608398	,	-75378957	),"ST", "Academic"),
		new Building("Campus Square", new GeoPoint(	40609473	,	-75378056	),"Residential"),
		new Building("Fairchild Library", new GeoPoint(	40609082	,	-75377830	),"Library"),
		new Building("Sinclair Laboratory ", new GeoPoint(	40609017	,	-75377176	),"SI", "Academic"),
		new Building("Neville Hall", new GeoPoint(	40608536	,	-75377272	),"NV", "Academic"),
		new Building("Mudd Building", new GeoPoint(	40608520	,	-75376629	),"MU", "Academic"),
		new Building("Whitaker Laboratory", new GeoPoint(	40608984	,	-75376307	),"WH", "Academic"),
		new Building("Packard Laboratory", new GeoPoint(	40607893	,	-75378946	),"PA", "Academic"),
		new Building("Philosophy Building", new GeoPoint(	40608047	,	-75378098	),"PH", "Academic"),
		new Building("Packer Memorial Church", new GeoPoint(	40607689	,	-75377616	),"Other"),
		new Building("Facilities Services/Planning", new GeoPoint(	40608658	,	-75375518	),"Service"),
		new Building("Zoellner Administration", new GeoPoint(	40608524	,	-75375497	),"Service"),
		new Building("Brodhead House", new GeoPoint(	40610027	,	-75380116	),"Residential"),
		new Building("Budget Office", new GeoPoint(	40609619	,	-75381601	),"Service"),
		new Building("Human Resources", new GeoPoint(	40609440	,	-75381612	),"Service"),
		new Building("Distance Education Office", new GeoPoint(	40609306	,	-75381529	),"Service"),
		new Building("Packer House", new GeoPoint(	40607999	,	-75381993	),"Residential"),
		new Building("ROTC House", new GeoPoint(	40607832	,	-75382599	),"Residential"),
		new Building("Humanities Center", new GeoPoint(	40607420	,	-75382036	),"Service"),
		new Building("Fraternity Management Association", new GeoPoint(	40607152	,	-75381913	),"Service"),
		new Building("Residency Life", new GeoPoint(	40606944	,	-75382047	),"Service"),
		new Building("Education Annex", new GeoPoint(	40607233	,	-75381746	),"Service"),
		new Building("Barnett House", new GeoPoint(	40607526	,	-75381634	),"Residential"),
		new Building("Mohler Laboratory	", new GeoPoint(	40607693	,	-7538136	),"MO", "Academic"),
		new Building("Controller’s Office", new GeoPoint(	40607314	,	-75381261	),"Service	"),
		new Building("Research and Sponsored Programs", new GeoPoint(	40607141	,	-75381269	),"Service"),
		new Building("Christmas Saucon Hall/Annex", new GeoPoint(	40608102	,	-75377144	),"XS", "Academic"),
		new Building("Chandler Ullmann Hall", new GeoPoint(	40607380	,	-75376441	),"CU", "Academic"),
		new Building("Fritz Engineering Laboratory", new GeoPoint(	40608027	,	-75376151	),"FR", "Academic"),
		new Building("Wilbur Annex", new GeoPoint(	40608105	,	-75375752	),"WB", "Academic"),
		new Building("Wilbur Powerhouse", new GeoPoint(	40607929	,	-75375556	),"WB", "Academic"),
		new Building("Sherman Fairchild Center", new GeoPoint(	40607428	,	-75375207	),"FM", "Academic"),
		new Building("Central Cooling & Refrigeration", new GeoPoint(	40608182	,	-75374799	),"	Service"),
		new Building("SCORE Lehigh Valley", new GeoPoint(	40608206	,	-75373877	),"Service"),
		new Building("Lehigh University Art Galleries", new GeoPoint(	40608593	,	-75372798	),"Service"),
		new Building("Zoellner Arts Center", new GeoPoint(	40607921	,	-7537275	),"ZA", "Academic"),
		new Building("Parking Garage", new GeoPoint(	40607972	,	-75372157	),"Other"),
		new Building("Taylor Gymnasium", new GeoPoint(	40607359	,	-75374123	),"Other"),
		};

	LocationManager lm;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);

		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_BUILDING_LIST);

			}
		});
	}
	
	@Override
	protected void onResume() {
		/*
		 * onResume is is always called after onStart, even if the app hasn't been
		 * paused
		 *
		 * add location listener and request updates every 1000ms or 10m
		 */
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		/* GPS, as it turns out, consumes battery like crazy */
		lm.removeUpdates(this);
		super.onResume();
	}


	protected Dialog onCreateDialog(int id) {
		/*This code creates arrays sorting buildings by name and abbr*/
		/*Building [] buildingsByName = new Building[campusBuildings.length];
		Building [] buildingsByAbbr = new Building[campusBuildings.length];
		String [] buildings_name = new String[campusBuildings.length];
		String [] buildings_abbr = new String[campusBuildings.length];
		for (int p = 0; p<campusBuildings.length; p++)
		{
			buildings_name[p] = campusBuildings[p].name;
			buildings_abbr[p] = campusBuildings[p].abbr;
		}
		Arrays.sort(buildings_name);
		Arrays.sort(buildings_abbr);
		boolean continue1;
		boolean continue2;
		for (int p = 0; p<campusBuildings.length; p++)
		{
			continue1 = true;
			continue2 = true;
			for (int q = 0; q<campusBuildings.length && (continue1 || continue2) ; q++)
			{
				if (buildings_name[p].equals(campusBuildings[q].name))
				{
					buildingsByName[p] = campusBuildings[q];
					continue1 = false;
				}
				if (buildings_abbr[p].equals(campusBuildings[q].abbr))
				{
					buildingsByAbbr[p] = campusBuildings[q];
					continue2 = false;
				}
			}
		}
		/*
		/*Now on to the dialog box*/
		Dialog d = null;
		switch (id) {
		case DIALOG_BUILDING_LIST:
			CharSequence[] items = new CharSequence[campusBuildings.length];
			for (int q = 0; q<campusBuildings.length; q++)
			{
				//items[q] = campusBuildings[q].name + " " + campusBuildings[q].abbr;
				items[q] = campusBuildings[q].name + " " + campusBuildings[q].abbr;
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Choose Destination");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
						setContentView(R.layout.map);
						MapView mapView;

						mapView = (MapView) findViewById(R.id.mapview);
						mapView.setBuiltInZoomControls(true);
						
						mapView.getController().setZoom(18);
						//mapView.getController().setCenter(campusBuildings[item].getCoordinates());
						mapView.getController().setCenter(campusBuildings[item].getCoordinates());
				}
			});
			AlertDialog alert = builder.create();
			d = alert;
			break;
		}
		return d;

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	

}
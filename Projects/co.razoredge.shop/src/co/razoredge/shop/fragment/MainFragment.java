/*
 * Copyright 2014 Jamison904
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.razoredge.shop.fragment;


import java.util.ArrayList;
import java.util.List;

import view.ScrollGridView;
import co.razoredge.shop.R;
import co.razoredge.shop.activity.AboutThemeActivity;
import co.razoredge.shop.activity.ApplyLauncherMain;
import co.razoredge.shop.activity.RequestActivity;
import co.razoredge.shop.activity.Wallpaper;
import co.razoredge.shop.adapter.MainAdapter;
import co.razoredge.shop.adapter.MainAdapter.AdapterItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import co.razoredge.shop.activity.*;
import android.net.*;
import android.widget.*;


/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

public class MainFragment extends SherlockFragment{
	
	ScrollGridView gridView;
	final List<AdapterItem> listOfStuff = new ArrayList<AdapterItem>();
	
	public static final int WEB = 0;
	public static final int GALLERY = 1;
	public static final int APPT = 2;
	public static final int SHOP = 3;
	public static final int PAY = 4;
	
	// This is the background layout that gets inflated behind the list view
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.gridview_behind, null);
	}
	
	// Starts when the MainFragment is launched
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
	/* 
	 * This part does two things
	 * First - It counts the number of items and displays them
	 * Second - It displays the text in the "" which is a brief description of that item
	 * Removing any of these will remove that item but be sure to edit ALL the cases below or your list
	 * won't line up properly
	 */
		
		/**
		 ** NOTE: in order to have different views on tablet vs phones, I added an if/else statement to this
		 ** section. Be sure to remove BOTH parts to remove it from phones and tablets. Failure to remove both
		 ** parts will result in the app functioning differently on phones and tablets.
		 **/

		/* 
		 * Sets the Title and description text for each GridView item
		 * Check res/values/strings.xml to change text to whatever you want each GridView to say
		 */
		boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
		if (tabletSize) {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_web), 
					getResources().getString (R.string.desc_web), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_gallery), 
					getResources().getString (R.string.desc_gallery), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_appt), 
					getResources().getString (R.string.desc_appt), 2));
			listOfStuff.remove(new AdapterItem(getResources().getString (R.string.title_shop), 
					getResources().getString (R.string.desc_shop), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_pay), 
					getResources().getString (R.string.desc_pay), 4));
			
			
		} else {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_web), 
					getResources().getString (R.string.desc_web), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_gallery), 
					getResources().getString (R.string.desc_gallery), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_appt), 
					getResources().getString (R.string.desc_appt), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_shop), 
					getResources().getString (R.string.desc_shop), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_pay), 
					getResources().getString (R.string.desc_pay), 4));
			
		}

		/**
		 ** NOTE: in order to have different views on tablet vs phones, I added an if/else statement to this
		 ** section. Be sure to remove both parts to remove it from phones and tablets. Failure to remove both
		 ** parts will result in the app functioning differently on phones and tablets.
		 **/
			MainAdapter adapter = new MainAdapter(getActivity(), listOfStuff);
	
			gridView.setAdapter(adapter);
			gridView.setExpanded(true);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					
					@SuppressWarnings("unused")
					MainFragment gridContentT = null;
					
					boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
					if (tabletSize) { // For TABLETS
						
						switch (position) {
					case WEB:
						Intent site =  new Intent(Intent.ACTION_VIEW).setData(Uri.parse
						("http://www.razoredge.co/"));
						startActivity(site); 
						break;
					case SHOP:
						Intent aboutShop = new Intent(getSherlockActivity(), AboutThemeActivity.class);
						startActivity(aboutShop);
						break;
					case GALLERY:
						Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
						startActivity(wall);
			        	break;
					case APPT:
						Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
						emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] 
						{getResources().getString(R.string.email_address2)});
						emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
						getResources().getText(R.string.email_subject2));
						emailIntent.setType("plain/text");
						startActivity(Intent.createChooser(emailIntent, "Appointment"));			
			        	break;
					case PAY:
						Intent payment = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
						("http://goo.gl/4CTOX2"));
						startActivity(payment);
						break;
					
						}	
				} else {	// For PHONES
					switch (position) {
					case WEB:
						Intent site =  new Intent(Intent.ACTION_VIEW).setData(Uri.parse
						("http://www.razoredge.co/"));
						startActivity(site); 
						break;
					case GALLERY:
						Intent wall = new Intent(getSherlockActivity(), Wallpaper.class);
						startActivity(wall);
		        		break;
					case APPT:
						Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
						emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] 
						{getResources().getString(R.string.email_address2)});
						emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
						getResources().getText(R.string.email_subject2));
						emailIntent.setType("plain/text");
						startActivity(Intent.createChooser(emailIntent, "Appointment"));			
						break;
					case SHOP:
						Intent aboutShop = new Intent(getSherlockActivity(), AboutThemeActivity.class);
						startActivity(aboutShop);
		        		break;	
					case PAY:
						Intent payment = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
						("http://goo.gl/4CTOX2"));
						startActivity(payment);
						break;
					
		        		
					}
				}
				}	
			});
			
	}
}

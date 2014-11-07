package com.infamous.watchdogs.fragment;


import java.util.ArrayList;
import java.util.List;

import view.ScrollGridView;
import com.infamous.watchdogs.R;
import com.infamous.watchdogs.activity.AboutThemeActivity;
import com.infamous.watchdogs.activity.ApplyLauncherMain;
import com.infamous.watchdogs.activity.RequestActivity;
import com.infamous.watchdogs.activity.Wallpaper;
import com.infamous.watchdogs.adapter.*;
import com.infamous.watchdogs.adapter.InfamousAdapter;
import com.infamous.watchdogs.adapter.InfamousAdapter.AdapterItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.infamous.watchdogs.activity.*;
import android.net.*;


/** 
 ** Some lines may be off a few numbers
 ** Just be sure you're in the general area
 **/

public class InfamousFragment extends SherlockFragment{
	
	ScrollGridView gridView;
	final List<AdapterItem> listOfStuff = new ArrayList<AdapterItem>();
	
	public static final int FORUM = 0;
	public static final int BLOG = 1;
	public static final int SOCIAL = 2;
	public static final int STORE = 3;
	public static final int DONATE = 4;
	public static final int BLACK = 5;

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
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_forum), 
					getResources().getString (R.string.desc_forum), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_blog), 
					getResources().getString (R.string.desc_blog), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_social), 
					getResources().getString (R.string.desc_social), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_store), 
					getResources().getString (R.string.desc_store), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_donate), 
					getResources().getString (R.string.desc_donate), 4));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_black), 
					getResources().getString (R.string.desc_black), 5));
			
		} else {
			gridView = (ScrollGridView)getView().findViewById(R.id.grid);
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_forum), 
					getResources().getString (R.string.desc_forum), 0));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_blog), 
					getResources().getString (R.string.desc_blog), 1));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_social), 
					getResources().getString (R.string.desc_social), 2));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_store), 
					getResources().getString (R.string.desc_store), 3));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_donate), 
					getResources().getString (R.string.desc_donate), 4));
			listOfStuff.add(new AdapterItem(getResources().getString (R.string.title_black), 
					getResources().getString (R.string.desc_black), 5)); 
			
		}

		/**
		 ** NOTE: in order to have different views on tablet vs phones, I added an if/else statement to this
		 ** section. Be sure to remove both parts to remove it from phones and tablets. Failure to remove both
		 ** parts will result in the app functioning differently on phones and tablets.
		 **/
			InfamousAdapter adapter = new InfamousAdapter(getActivity(), listOfStuff);
	
			gridView.setAdapter(adapter);
			gridView.setExpanded(true);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					
					@SuppressWarnings("unused")
					MainFragment gridContentT = null;
					
					boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
					if (tabletSize) { // For TABLETS
						
						switch (position) {
							case FORUM:
								Intent forum = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://infamousdevelopment.com/forum"));
								startActivity(forum);
								break;
							case BLOG:
								Intent blog = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://infamousdevelopment.com"));
								startActivity(blog);
								break;
							case SOCIAL:
								Intent social = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://infamousdevelopment.com/social"));
								startActivity(social);
								break;
							case STORE:
								Intent store = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://goo.gl/zsjREb"));
								startActivity(store);
								break;
							case DONATE:
								Intent donate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://goo.gl/Gw2Lf9"));
								startActivity(donate);
								break;
							case BLACK:
								Intent black = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
								("http://goo.gl/y2wnal"));
								startActivity(black);
								break;
						}	
				} else {	// For PHONES
					switch (position) {
						case FORUM:
							Intent forum = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("http://infamousdevelopment.com/forum"));
							startActivity(forum);
							break;
						case BLOG:
							Intent blog = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("http://infamousdevelopment.com"));
							startActivity(blog);
							break;
						case SOCIAL:
							Intent social = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("http://infamousdevelopment.com/social"));
							startActivity(social);
							break;
						case STORE:
							Intent store = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("http://goo.gl/zsjREb"));
							startActivity(store);
							break;
						case DONATE:
							Intent donate = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("http://goo.gl/Gw2Lf9"));
							startActivity(donate);
							break;
						case BLACK:
							Intent black = new Intent(Intent.ACTION_VIEW).setData(Uri.parse
							("http://goo.gl/y2wnal"));
							startActivity(black);
							break;
		        		
					}
				}
				}	
			});
			
	}
}

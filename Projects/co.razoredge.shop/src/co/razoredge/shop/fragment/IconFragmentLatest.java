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

import co.razoredge.shop.R;
import co.razoredge.shop.util.IconsProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

	public class IconFragmentLatest extends Fragment implements AdapterView.OnItemClickListener{
	    private static final String RESULT_OK = null;
		public Uri CONTENT_URI;

	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View view = inflater.inflate(R.layout.all_icons_main, container, false);
	        int iconSize=getResources().getDimensionPixelSize(R.dimen.allapps_icon_preview);
	        GridView gridview = (GridView) view.findViewById(R.id.icon_grid);
	        gridview.setAdapter(new IconAdapter(getActivity(), iconSize));
	        CONTENT_URI=Uri.parse("content://"+IconsProvider.class.getCanonicalName());
			return view;
			
	    }

		@Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
	        String icon=adapterView.getItemAtPosition(i).toString();
	        Intent result = new Intent(null, Uri.withAppendedPath(CONTENT_URI,icon));
	        setResult(RESULT_OK, result);
	        finish();
	    }
	    private void setResult(String resultOk, Intent result) {

		}
		private void finish() {

		}
		
		private class IconAdapter extends BaseAdapter{
			private Context mContext;
			public IconAdapter(Context mContext, int iconsize) {
				this.mContext = mContext;
				loadIcon();
			}

	        @Override
	        public int getCount() {
	            return mThumbs.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            return mThumbs.get(position);
	        }

	        @Override
	        public long getItemId(int position) {
	            return position;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ImageView imageView;
	            if (convertView == null) {
	                imageView = new ImageView(mContext);
	                imageView.setLayoutParams(new GridView.LayoutParams(144, 144));
	            } else {
	                imageView = (ImageView) convertView;
	            }
	            imageView.setImageResource(mThumbs.get(position));
	            return imageView;
			}

	        private ArrayList<Integer> mThumbs;
	        ////////////////////////////////////////////////
	        private void loadIcon() {
	            mThumbs = new ArrayList<Integer>();

	            final Resources resources = getResources();
	            final String packageName = getActivity().getApplication().getPackageName();
	            
	            /** TODO
	             * Change array
	             */
	            addIcon(resources, packageName, R.array.latesticons);

	        }
	        private void addIcon(Resources resources, String packageName, int list) {
	            final String[] extras = resources.getStringArray(list);
	            for (String extra : extras) {
	                int res = resources.getIdentifier(extra, "drawable", packageName);
	                if (res != 0) {
	                    final int thumbRes = resources.getIdentifier(extra,"drawable", packageName);
	                    if (thumbRes != 0) {
	                        mThumbs.add(thumbRes);
	                    }
	                }
	            }
	        }

	    }
	}

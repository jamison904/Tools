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
 
package co.razoredge.shop.activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;

import java.util.ArrayList;

import co.razoredge.shop.R;

public class Docks extends SherlockActivity implements AdapterView.OnItemClickListener{
    public Uri CONTENT_URI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView lv=new ListView(this);
        lv.setAdapter(new DocksAdapter(this));
        lv.setOnItemClickListener(this);
        setContentView(lv);
        CONTENT_URI=Uri.parse("content://"+DocksProvider.class.getCanonicalName());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String dock=adapterView.getItemAtPosition(i).toString();
        Intent result = new Intent(null, Uri.withAppendedPath(CONTENT_URI,dock));
        setResult(RESULT_OK, result);
        finish();
    }
    private class DocksAdapter extends BaseAdapter{
        public DocksAdapter(Context mContext) {
            super();
            loadDocks();
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
            if(convertView==null){
                convertView=new ImageView(Docks.this);
                convertView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, ListView.LayoutParams.WRAP_CONTENT));
            }
            ((ImageView)convertView).setImageResource(mThumbs.get(position));
            return convertView;
        }

        private ArrayList<Integer> mThumbs;
        ////////////////////////////////////////////////
        private void loadDocks() {
            mThumbs = new ArrayList<Integer>();

            final Resources resources = getResources();
            final String packageName = getApplication().getPackageName();

            addDocks(resources, packageName, R.array.dock_pack);
        }
        private void addDocks(Resources resources, String packageName, int list) {
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

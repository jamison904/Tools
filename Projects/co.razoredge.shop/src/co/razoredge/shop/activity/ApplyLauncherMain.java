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

import co.razoredge.shop.R;
import co.razoredge.shop.fragment.ApplyLauncherFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

public class ApplyLauncherMain extends FragmentActivity {

	Button btnCancel;

	// Starts the Activity for the list view
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apply_launcher_main);
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container_launcher, 
				new ApplyLauncherFragment())
		.commit();

	}

	// This will return the Activity to the Main Screen when the app is in a Paused (not used) state
	@Override
	  public void onPause(){
		  super.onPause();
		  finish();
	  }
}

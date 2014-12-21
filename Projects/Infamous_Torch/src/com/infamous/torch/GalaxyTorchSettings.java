/*
 * Copyright 2014 Infamous Development
 */

package com.infamous.torch;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class GalaxyTorchSettings extends PreferenceActivity {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}

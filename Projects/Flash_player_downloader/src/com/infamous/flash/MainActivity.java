package com.infamous.flash;

	import android.app.*;
	import android.os.*;
	import android.view.*;
	import android.net.Uri;
	import android.content.Intent;
	import android.widget.ImageButton;
	import android.widget.Toast;
	import android.widget.*;
	import android.view.View.*;

	public class MainActivity extends Activity
	{
		

		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);

			addButtonListener();
		}

		public void addButtonListener() {
			Button mainButton = (Button) findViewById(R.id.mainButton);
			mainButton.setOnClickListener(new OnClickListener() {
					public void onClick(View View)
					{
						Intent openBrowser =  new Intent(Intent.ACTION_VIEW,
														 Uri.parse("https://github.com/InfamousProductions/InfamousProductions.github.io/blob/master/download/install_flash_player_kk.apk?raw=true"));
						startActivity(openBrowser);

						Toast.makeText(MainActivity.this,"Check out my other apps!", Toast.LENGTH_LONG).show();

					}
				});

			Button mainButton1 = (Button)findViewById(R.id.mainButton1);
			mainButton1.setOnClickListener(new OnClickListener() {
					public void onClick(View View)
					{
						Intent openBrowser =  new Intent(Intent.ACTION_VIEW,
														 Uri.parse("https://github.com/InfamousProductions/InfamousProductions.github.io/blob/master/download/install_flash_player_ics.apk?raw=true"));
						startActivity(openBrowser);

						Toast.makeText(MainActivity.this,"Check out my other apps!", Toast.LENGTH_LONG).show();

					}
				});
			
				
			Button mainButton2 = (Button) findViewById(R.id.mainButton2);
			mainButton2.setOnClickListener(new OnClickListener() {
					public void onClick(View View)
					{
						Intent openBrowser =  new Intent(Intent.ACTION_VIEW,
														 Uri.parse("https://github.com/InfamousProductions/InfamousProductions.github.io/blob/master/download/install_flash_player_pre_ics.apk?raw=true"));
						startActivity(openBrowser);

						Toast.makeText(MainActivity.this,"Check out my other apps!", Toast.LENGTH_LONG).show();

					}
				});
			
				
				
			Button mainButton3 = (Button)findViewById(R.id.mainButton3);
			mainButton3.setOnClickListener(new OnClickListener(){
					public void onClick(View p1)
					{
						finish();
					}
				});
		}
	}


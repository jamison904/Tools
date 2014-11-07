package com.infamous.watchdogs.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.infamous.watchdogs.R;


public class InfamousAdapter extends BaseAdapter{
	
	
	public static final int FORUM = 0;
	public static final int BLOG = 1;
	public static final int SOCIAL = 2;
	public static final int STORE = 3;
	public static final int DONATE = 4;
	public static final int BLACK = 5;
	
	
	
	private Context context;
	private List<AdapterItem> gridItem;

	public InfamousAdapter(Context context, List<AdapterItem> gridItem) {
		this.gridItem = gridItem;
		this.context = context;
	}

	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder;
		AdapterItem entry = gridItem.get(position);
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			v = inflater.inflate(R.layout.gridview_layout, null);
			
			holder = new ViewHolder();
			holder.title = (TextView) v.findViewById(R.id.title);
			holder.text = (TextView) v.findViewById(R.id.description);
			holder.icon_Image = (ImageView) v.findViewById(R.id.list_image);
			
			v.setTag(holder);
		}
		else {
			holder = (ViewHolder) v.getTag();
		}
			holder.title.setText(entry.getTitle());

			/* 
			 * Sets the font type for the title and description of each item
			 * This is if you want to have a bolder font for title or something
			 * Make sure the font file is in the projects Asset folder
			 * Default for this template is Roboto-Thin
			 * themefont.ttf is the font the theme grabs also
			 */
		Typeface tfTitle = Typeface.createFromAsset(context.getAssets(),"DroidSerif-Bold.ttf");
		Typeface tfDescription = Typeface.createFromAsset(context.getAssets(),"DroidSerif-Regular.ttf");
			holder.title.setTypeface(tfTitle);
			holder.text.setTypeface(tfDescription);
			
			/* 
			 * Sets the description and title text color as well as icon shown
			 * You can reference any color in the colors.xml and even add some
			 * You can also individually set the color for each GridView by 
			 * referencing a different color on each case statement (kinda like the different icon references)
			 * You can reference any drawable
			 */
			switch(entry.getID()){
			
			case FORUM:
				holder.title.setTextColor(context.getResources().getColor(R.color.list_title_color));
				holder.text.setTextColor(context.getResources().getColor(R.color.list_desc_color));
				holder.icon_Image.setImageResource(R.drawable.icon_forum);
				break;
			case BLOG:
				holder.title.setTextColor(context.getResources().getColor(R.color.list_title_color));
				holder.text.setTextColor(context.getResources().getColor(R.color.list_desc_color));
				holder.icon_Image.setImageResource(R.drawable.icon_blog);
				break;
			case SOCIAL:
				holder.title.setTextColor(context.getResources().getColor(R.color.list_title_color));
				holder.text.setTextColor(context.getResources().getColor(R.color.list_desc_color));
				holder.icon_Image.setImageResource(R.drawable.icon_social);
				break;
			case STORE:
				holder.title.setTextColor(context.getResources().getColor(R.color.list_title_color));
				holder.text.setTextColor(context.getResources().getColor(R.color.list_desc_color));
				holder.icon_Image.setImageResource(R.drawable.icon_play);
				break;
			case DONATE:
				holder.title.setTextColor(context.getResources().getColor(R.color.list_title_color));
				holder.text.setTextColor(context.getResources().getColor(R.color.list_desc_color));
				holder.icon_Image.setImageResource(R.drawable.icon_paypal);
				break;
			case BLACK:
				holder.title.setTextColor(context.getResources().getColor(R.color.list_title_color));
				holder.text.setTextColor(context.getResources().getColor(R.color.list_desc_color));
				holder.icon_Image.setImageResource(R.drawable.icon_blackedout);
				break;
			
			}
			holder.text.setText(entry.getDescription());
			holder.title.setText(entry.getTitle());
			
		return v;
	}

	@Override
	public int getCount() {
		return gridItem.size();
	}

	@Override
	public Object getItem(int position) {
		return gridItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static class ViewHolder {
		public TextView title;
		public TextView text;
		public ImageView icon_Image;
	}
	
	public static class AdapterItem{
		String Title;
		String Description;
		int ID;
		
		public AdapterItem(String Title, String Description, int ID) {
			this.Title = Title;
			this.Description = Description;
			this.ID = ID;
		}
		
		public String getTitle() {
			return Title;
		}
		
		public String getDescription() {
			return Description;
		}
		
		public int getID() {
			return ID;
		}
	}
}

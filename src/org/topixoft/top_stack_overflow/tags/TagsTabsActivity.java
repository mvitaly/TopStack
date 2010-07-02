package org.topixoft.top_stack_overflow.tags;

import org.topixoft.top_stack_overflow.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TabHost;

import com.google.code.stackexchange.schema.Tag;

public class TagsTabsActivity extends TabActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_and_tabs);
		
        setupTabs();
    }

	private void setupTabs() {
		Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Drawable drawable;
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, TagsActivity.class);
	    intent.putExtra(TagsActivity.TAGS_SOURCE_EXTRA, new TagsSortOrderSource(Tag.SortOrder.LEAST_POPULAR));

		// Initialize a TabSpec for each tab and add it to the TabHost
	    drawable = res.getDrawable(android.R.drawable.ic_menu_sort_by_size);
	    drawable.setLevel(1);
		spec = tabHost.newTabSpec("popular")
				.setIndicator("Popular", new ScaleDrawable(drawable, Gravity.RIGHT, 50f, 50f))
				.setContent(intent);
		tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, TagsActivity.class);
	    intent.putExtra(TagsActivity.TAGS_SOURCE_EXTRA, new TagsSortOrderSource(Tag.SortOrder.NAME_ASCENDING));
	    drawable = res.getDrawable(android.R.drawable.ic_menu_sort_alphabetically);
	    drawable.setLevel(1);
	    spec = tabHost.newTabSpec("name")
	    		.setIndicator("Name", new ScaleDrawable(drawable, Gravity.RIGHT, 50f, 50f))
	    		.setContent(intent);
	    tabHost.addTab(spec);

//	    tabHost.setCurrentTab(1);
	}
	
}

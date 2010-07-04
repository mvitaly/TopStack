package org.topixoft.top_stack_overflow.users;

import org.topixoft.top_stack_overflow.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TabHost;

import com.google.code.stackexchange.schema.User;

public class UsersTabsActivity extends TabActivity {
	
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
	    intent = new Intent().setClass(this, UsersActivity.class);
	    intent.putExtra(UsersActivity.USERS_SOURCE_EXTRA, new UsersSortOrderSource(User.SortOrder.LEAST_REPUTED));

		// Initialize a TabSpec for each tab and add it to the TabHost
	    drawable = res.getDrawable(android.R.drawable.ic_menu_sort_by_size);
	    drawable.setLevel(1);
		spec = tabHost.newTabSpec("reputation")
				.setIndicator("Reputation", new ScaleDrawable(drawable, Gravity.RIGHT, 50f, 50f))
				.setContent(intent);
		tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, UsersActivity.class);
	    intent.putExtra(UsersActivity.USERS_SOURCE_EXTRA, new UsersSortOrderSource(User.SortOrder.MOST_RECENTLY_CREATED));
	    drawable = res.getDrawable(android.R.drawable.ic_menu_sort_alphabetically);
	    drawable.setLevel(1);
	    spec = tabHost.newTabSpec("newest")
	    		.setIndicator("Newest", new ScaleDrawable(drawable, Gravity.RIGHT, 50f, 50f))
	    		.setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, UsersActivity.class);
	    intent.putExtra(UsersActivity.USERS_SOURCE_EXTRA, new UsersSortOrderSource(User.SortOrder.LEAST_RECENTLY_CREATED));
	    drawable = res.getDrawable(android.R.drawable.ic_menu_sort_alphabetically);
	    drawable.setLevel(1);
	    spec = tabHost.newTabSpec("oldest")
	    		.setIndicator("Oldest", new ScaleDrawable(drawable, Gravity.RIGHT, 50f, 50f))
	    		.setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, UsersActivity.class);
	    intent.putExtra(UsersActivity.USERS_SOURCE_EXTRA, new UsersSortOrderSource(User.SortOrder.NAME_ASCENDING));
	    drawable = res.getDrawable(android.R.drawable.ic_menu_sort_alphabetically);
	    drawable.setLevel(1);
	    spec = tabHost.newTabSpec("name")
	    		.setIndicator("Name", new ScaleDrawable(drawable, Gravity.RIGHT, 50f, 50f))
	    		.setContent(intent);
	    tabHost.addTab(spec);

//	    tabHost.setCurrentTab(1);
	}
	
}

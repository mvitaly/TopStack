package org.topixoft.top_stack_overflow.questions;

import org.topixoft.top_stack_overflow.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.code.stackexchange.schema.Question;

public class QuestionsTabsActivity extends TabActivity implements OnClickListener {
	
	public static final String QUESTION_SEARCH_STRING = "questionSearchString";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_and_tabs);
		
		findViewById(R.id.imgbtnSearch).setOnClickListener(this);
		
		String searchString = null;
		Intent intent = getIntent();
		if (intent.getExtras() != null) {
			searchString = intent.getExtras().getString(QUESTION_SEARCH_STRING);
		}

		TextView textviewSearch = (TextView) findViewById(R.id.textviewSearch);
		textviewSearch.setText(searchString);

		if (searchString == null || searchString.length() == 0) {
			setupTabs();
		} else {
			setupTabs(searchString);
		}
    }

	private void setupTabs() {
		Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSortOrderSource(Question.SortOrder.CREATION));

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("newest")
				.setIndicator("Newest")
				.setContent(intent);
		tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSortOrderSource(Question.SortOrder.FEATURED));
	    spec = tabHost.newTabSpec("featured").setIndicator("Featured")
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSortOrderSource(Question.SortOrder.HOT));
	    spec = tabHost.newTabSpec("hot").setIndicator("Hot")
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSortOrderSource(Question.SortOrder.VOTES));
	    spec = tabHost.newTabSpec("votes").setIndicator("Votes")
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSortOrderSource(Question.SortOrder.ACTIVITY));
	    spec = tabHost.newTabSpec("active").setIndicator("Active")
	                  .setContent(intent);
	    tabHost.addTab(spec);

//	    tabHost.setCurrentTab(1);
	}

	private void setupTabs(String searchString) {
		Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSearchSource(searchString, Question.SortOrder.CREATION));

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("newest")
				.setIndicator("Newest")
				.setContent(intent);
		tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSearchSource(searchString, Question.SortOrder.VOTES));
	    spec = tabHost.newTabSpec("votes").setIndicator("Votes")
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsSearchSource(searchString, Question.SortOrder.ACTIVITY));
	    spec = tabHost.newTabSpec("active").setIndicator("Active")
	                  .setContent(intent);
	    tabHost.addTab(spec);

//	    tabHost.setCurrentTab(1);
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.imgbtnSearch) {
			TextView textviewSearch = (TextView) findViewById(R.id.textviewSearch);
			Intent intent = new Intent(getIntent());
			intent.putExtra(QUESTION_SEARCH_STRING, textviewSearch.getText().toString());
			startActivity(intent);
		}
	}
	
}

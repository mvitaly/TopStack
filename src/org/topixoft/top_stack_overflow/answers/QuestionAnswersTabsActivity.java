package org.topixoft.top_stack_overflow.answers;

import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.questions.QuestionsActivity;
import org.topixoft.top_stack_overflow.questions.QuestionsSortOrderSource;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.code.stackexchange.schema.Answer;
import com.google.code.stackexchange.schema.Question;

public class QuestionAnswersTabsActivity extends TabActivity {
	
	public static final String QUESTION_EXTRA = "question";
	
	private Question question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();
		question = (Question) extras.getSerializable(QUESTION_EXTRA);

		setContentView(R.layout.question_and_tabs);
		
		TextView textviewQuestionTitle = (TextView) findViewById(R.id.textviewQuestionTitle);
		textviewQuestionTitle.setText(question.getTitle());
		
        setupTabs();
    }

	private void setupTabs() {
		Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, QuestionAnswersActivity.class);
	    intent.putExtra(QuestionAnswersActivity.ANSWERS_SOURCE_EXTRA, new AnswersSortOrderSource(question.getQuestionId(), Answer.SortOrder.LEAST_RECENTLY_UPDATED));

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("oldest")
				.setIndicator("Oldest")
				.setContent(intent);
		tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, QuestionAnswersActivity.class);
	    intent.putExtra(QuestionAnswersActivity.ANSWERS_SOURCE_EXTRA, new AnswersSortOrderSource(question.getQuestionId(), Answer.SortOrder.MOST_RECENTLY_UPDATED));
	    spec = tabHost.newTabSpec("newest").setIndicator("Newest")
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, QuestionAnswersActivity.class);
	    intent.putExtra(QuestionAnswersActivity.ANSWERS_SOURCE_EXTRA, new AnswersSortOrderSource(question.getQuestionId(), Answer.SortOrder.MOST_VOTED));
	    spec = tabHost.newTabSpec("votes").setIndicator("Votes")
	                  .setContent(intent);
	    tabHost.addTab(spec);

//	    tabHost.setCurrentTab(1);
	}
	
}

package org.topixoft.top_stack_overflow;

import org.topixoft.top_stack_overflow.badges.BadgesTabsActivity;
import org.topixoft.top_stack_overflow.questions.QuestionsTabsActivity;
import org.topixoft.top_stack_overflow.tags.TagsTabsActivity;
import org.topixoft.top_stack_overflow.users.UsersTabsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TopStackOverflowActivity extends Activity implements View.OnClickListener {
	
	public static final boolean DEBUG_MODE = false;

	public static final String STACK_OVERFLOW_API_KEY = "C9yKKRBnF0CJlmfsKQ4ppA";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.buttonQuestions).setOnClickListener(this);
        findViewById(R.id.buttonTags).setOnClickListener(this);
        findViewById(R.id.buttonUsers).setOnClickListener(this);
        findViewById(R.id.buttonBadges).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
    	Intent intent = null;
    	switch (v.getId()) {
    		case R.id.buttonQuestions:
				intent = new Intent(this, QuestionsTabsActivity.class);
    			break;
    		case R.id.buttonTags:
				intent = new Intent(this, TagsTabsActivity.class);
    			break;
    		case R.id.buttonUsers:
    			intent = new Intent(this, UsersTabsActivity.class);
    			break;
    		case R.id.buttonBadges:
    			intent = new Intent(this, BadgesTabsActivity.class);
    			break;
    		default:
    			return;
    	}
    	
    	if (intent != null) {
    		startActivity(intent);
    	}
    }
    
}
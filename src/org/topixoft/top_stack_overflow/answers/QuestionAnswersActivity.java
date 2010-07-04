package org.topixoft.top_stack_overflow.answers;

import java.io.Serializable;

import org.topixoft.top_stack_overflow.AbstractListActivity;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.questions.QuestionDetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.code.stackexchange.schema.Answer;

public class QuestionAnswersActivity extends AbstractListActivity<Answer> implements AdapterView.OnItemClickListener {

	public static final String ANSWERS_SOURCE_EXTRA = "answersSource";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AnswersSource source = null;
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			source = (AnswersSource) extras.get(ANSWERS_SOURCE_EXTRA);
		}
		
		super.onCreate(savedInstanceState);
		
		new AnswersListAdapter(this, source, this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(QuestionAnswersActivity.this, QuestionDetailsActivity.class);
		intent.putExtra(QuestionDetailsActivity.QUESTION_EXTRA, (Serializable) parent.getItemAtPosition(position));
		startActivity(intent);
	}

}

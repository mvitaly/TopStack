package org.topixoft.top_stack_overflow.questions;

import java.io.Serializable;

import org.topixoft.top_stack_overflow.AbstractListActivity;
import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.code.stackexchange.schema.Question;

public class QuestionsActivity extends AbstractListActivity<Question> implements AdapterView.OnItemClickListener {

	public static final String QUESTIONS_SOURCE_EXTRA = "questionsSource";
	
	@Override
	protected int getContentViewId() {
		return R.layout.questions;
	}
	
	@Override
	protected int getListViewId() {
		return R.id.listviewQuestions;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		PagableSource<Question, ?> source = null;
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			source = (PagableSource<Question, ?>) extras.get(QUESTIONS_SOURCE_EXTRA);
		}
		
		super.onCreate(savedInstanceState);
		
		new QuestionsListAdapter(this, source, this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(QuestionsActivity.this, QuestionDetailsActivity.class);
		intent.putExtra(QuestionDetailsActivity.QUESTION_EXTRA, (Serializable) parent.getItemAtPosition(position));
		startActivity(intent);
	}

}

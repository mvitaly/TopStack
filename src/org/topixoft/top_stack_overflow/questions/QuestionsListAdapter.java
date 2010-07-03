package org.topixoft.top_stack_overflow.questions;

import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.code.stackexchange.schema.Question;

public class QuestionsListAdapter extends PagableListAdapter<Question> {

	Resources resources;
	
	public QuestionsListAdapter(Activity activity, PagableSource<Question, ?> source, ItemsUpdater<Question> itemsUpdater) {
		super(activity, source, itemsUpdater);
		resources = activity.getResources();
	}

	@Override
	public View getViewInternal(int position, View convertView, ViewGroup parent) {
		View view;
		
		if (convertView == null || convertView.getId() != R.id.layoutQuestionRow) {
            view = inflater.inflate(R.layout.question_row, parent, false);
        } else {
            view = convertView;
        }
		
		populateView(getItem(position), view);
		
        return view;
	}

	private void populateView(Question question, View view) {
		TextView textviewVotes = (TextView) view.findViewById(R.id.textviewVotes);
		LinearLayout layoutAnswers = (LinearLayout) view.findViewById(R.id.layoutAnswers);
		TextView textviewAnswers = (TextView) view.findViewById(R.id.textviewAnswers);
		TextView textviewAnswersLabel = (TextView) view.findViewById(R.id.textviewAnswersLabel);
		TextView textviewViews = (TextView) view.findViewById(R.id.textviewViews);
		TextView textviewQuestion = (TextView) view.findViewById(R.id.textviewQuestion);
		LinearLayout layoutTags = (LinearLayout) view.findViewById(R.id.layoutTags);
		
		int answers_color;
		if (question.getAcceptedAnswerId() > 0) {
			answers_color = resources.getColor(R.color.answer_selected_color);
		} else {
			answers_color = resources.getColor(R.color.answers_color);
		}
		textviewAnswers.setTextColor(answers_color);
		textviewAnswersLabel.setTextColor(answers_color);
		
//		Log.d(this.getClass().getSimpleName(), "Q[" + question.getAnswerCount() + "]: " + question.getTitle());
		if (question.getAnswerCount() < 1) {
			layoutAnswers.setBackgroundResource(R.color.no_answer_background_color);
		} else {
			layoutAnswers.setBackgroundResource(R.color.answers_background_color);
		}
		
		textviewVotes.setText(beautifyAndStringify(question.getUpVoteCount()));
		textviewAnswers.setText(beautifyAndStringify(question.getAnswerCount()));
		textviewViews.setText(beautifyAndStringify(question.getViewCount()));
		textviewQuestion.setText(question.getTitle());
		
		layoutTags.removeAllViews();
		for (String tag : question.getTags()) {
			View tagView = inflater.inflate(R.layout.question_row_tag, layoutTags, false);
			TextView textviewTag = (TextView) tagView.findViewById(R.id.textviewTag);
			textviewTag.setText(tag);
			layoutTags.addView(tagView);
		}
	}
	
	@Override
	public String getItemsString() {
		return "questions";
	}
	
	@Override
	public long getItemIdInternal(Question item) {
		return item.getQuestionId();
	}
	
}

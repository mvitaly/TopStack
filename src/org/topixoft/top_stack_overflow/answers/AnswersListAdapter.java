package org.topixoft.top_stack_overflow.answers;

import org.topixoft.top_stack_overflow.HtmlBodyUtils;
import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.google.code.stackexchange.schema.Answer;

public class AnswersListAdapter extends PagableListAdapter<Answer> {

	Resources resources;
	
	public AnswersListAdapter(Activity activity, AnswersSource source, ItemsUpdater<Answer> itemsUpdater) {
		super(activity, source, itemsUpdater);
		resources = activity.getResources();
	}

	@Override
	public View getViewInternal(int position, View convertView, ViewGroup parent) {
		View view;
		
		if (convertView == null || convertView.getId() != R.id.layoutQuestionRow) {
            view = inflater.inflate(R.layout.answer_row, parent, false);
        } else {
            view = convertView;
        }
		
		populateView(getItem(position), view);
		
        return view;
	}

	private void populateView(Answer answer, View view) {
		ViewGroup viewGroup = (ViewGroup) view;
		
		viewGroup.removeAllViews();
		
		WebView webviewQuestionAnswer = new WebView(activity);
		
		String questionAnswerHtml = HtmlBodyUtils.getBodyHtml(answer.getBody());
		webviewQuestionAnswer.loadData(questionAnswerHtml, "text/html", "utf-8");
		
		viewGroup.addView(webviewQuestionAnswer,
				new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
	@Override
	public String getItemsString() {
		return "answers";
	}
	
	@Override
	public long getItemIdInternal(Answer item) {
		return item.getQuestionId();
	}
	
}

package org.topixoft.top_stack_overflow.questions;

import java.util.EnumSet;
import java.util.List;

import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.TopStackOverflowActivity;
import org.topixoft.top_stack_overflow.answers.QuestionAnswersTabsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.code.stackexchange.client.StackExchangeApiClient;
import com.google.code.stackexchange.client.StackExchangeApiClientFactory;
import com.google.code.stackexchange.schema.Answer;
import com.google.code.stackexchange.schema.FilterOption;
import com.google.code.stackexchange.schema.Question;

public class QuestionDetailsActivity extends Activity implements View.OnClickListener {
	
	private static final String TAG = "QuestionDetailsActivity";
	
	public static final String QUESTION_EXTRA = "question";
	
	private Question question;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle extras = getIntent().getExtras();
		question = (Question) extras.getSerializable(QUESTION_EXTRA);
		
		setContentView(R.layout.question_details);
		
		TextView textviewQuestionTitle = (TextView) findViewById(R.id.textviewQuestionTitle);
		textviewQuestionTitle.setText(question.getTitle());

		ProgressBar progressbar = (ProgressBar) findViewById(R.id.progressbarLoadingLoadNext);
		progressbar.setVisibility(View.VISIBLE);

		Button buttonQuestionDetailsSeeAllAnswers = (Button) findViewById(R.id.buttonQuestionDetailsSeeAllAnswers);
		buttonQuestionDetailsSeeAllAnswers.setOnClickListener(this);

		new GetQuestionDetilasAsyncTask().execute(question.getQuestionId());
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, QuestionAnswersTabsActivity.class);
		intent.putExtra(QuestionAnswersTabsActivity.QUESTION_EXTRA, question);
		startActivity(intent);
	}
	
	private class GetQuestionDetilasAsyncTask extends AsyncTask<Long, Void, Question> {
		@Override
		protected Question doInBackground(Long... questionId) {
			Log.d(TAG, "Retrieveing question details");
			try {
				return getQuestionWithSelectedAnswer(questionId[0]);
			} catch (Exception e) {
				Log.e(TAG, "Erro retrieveing question details", e);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(Question question) {
			populateViewWithQuestionDetails(question);
		}
	}
	
	public void populateViewWithQuestionDetails(Question question) {
		View layoutQuestionDetialsLoaded = findViewById(R.id.layoutQuestionDetialsLoaded);
		View viewQuestionDetailsLoading = findViewById(R.id.viewQuestionDetailsLoading);
		ViewGroup layoutQuestionBody = (ViewGroup) findViewById(R.id.layoutQuestionBody);
		ViewGroup layoutQuestionSelectedAnswer = (ViewGroup) findViewById(R.id.layoutQuestionSelectedAnswer);
		Button buttonQuestionDetailsSeeAllAnswers = (Button) findViewById(R.id.buttonQuestionDetailsSeeAllAnswers);

		layoutQuestionDetialsLoaded.setVisibility(View.VISIBLE);
		viewQuestionDetailsLoading.setVisibility(View.GONE);
		
		WebView webviewQuestionBody = new WebView(this);
		
		String questionBodyHtml = getQuestionBodyHtml(question.getBody());
		webviewQuestionBody.loadData(questionBodyHtml, "text/html", "utf-8");

		layoutQuestionBody.addView(webviewQuestionBody,
				new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		if (question.getAcceptedAnswerId() > 0) {
			Answer answer = question.getAnswers().get(0);
			
			layoutQuestionSelectedAnswer.setVisibility(View.VISIBLE);
			
			WebView webviewQuestionAnswer = new WebView(this);
			
			String questionAnswerHtml = getQuestionBodyHtml(answer.getBody());
			webviewQuestionAnswer.loadData(questionAnswerHtml, "text/html", "utf-8");
			
			layoutQuestionSelectedAnswer.addView(webviewQuestionAnswer,
					new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		
		if (question.getAnswerCount() > 0) {
			buttonQuestionDetailsSeeAllAnswers.setVisibility(View.VISIBLE);
			buttonQuestionDetailsSeeAllAnswers.setText(
					getResources().getString(R.string.question_details_see_all_answers, question.getAnswerCount())
					);
		}
	}
	
	private String getQuestionBodyHtml(String body) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<html>\n" +
				"<head>\n" +
				"</head>\n" +
				"</body>\n");
		
		sb.append(body);
		
		sb.append("\n" +
				"</body>\n" +
				"</html>\n");
		return sb.toString();
	}

	public Question getQuestionWithSelectedAnswer(long questionId) {
		StackExchangeApiClientFactory factory = StackExchangeApiClientFactory.newInstance(TopStackOverflowActivity.STACK_OVERFLOW_API_KEY);
		StackExchangeApiClient client = factory.createStackOverflowApiClient();
		Question question = client.getQuestions(
				EnumSet.of(
						FilterOption.INCLUDE_BODY,
						FilterOption.INCLUDE_COMMENTS/*,
						FilterOption.INCLUDE_ANSWERS*/),
				questionId
				)
				.get(0);
		
		if (question.getAcceptedAnswerId() > 0) {
			List<Answer> acceptedAnswerList = client.getAnswers(
					EnumSet.of(
							FilterOption.INCLUDE_BODY,
							FilterOption.INCLUDE_COMMENTS
							),
					question.getAcceptedAnswerId());
			question.setAnswers(acceptedAnswerList);
		}
		
		return question;
	}
	
}

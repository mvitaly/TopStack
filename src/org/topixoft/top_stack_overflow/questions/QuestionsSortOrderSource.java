package org.topixoft.top_stack_overflow.questions;

import com.google.code.stackexchange.client.query.QuestionApiQuery;
import com.google.code.stackexchange.schema.Question;

@SuppressWarnings("serial")
public class QuestionsSortOrderSource extends QuestionsSource {
	
	private Question.SortOrder sortOrder;
	
	public QuestionsSortOrderSource(Question.SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Override
	public QuestionApiQuery customizeQuery(QuestionApiQuery query) {
		return query.withSort(sortOrder);
	}

}

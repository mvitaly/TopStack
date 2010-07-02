package org.topixoft.top_stack_overflow.answers;

import com.google.code.stackexchange.client.query.AnswerApiQuery;
import com.google.code.stackexchange.schema.Answer;

@SuppressWarnings("serial")
public class AnswersSortOrderSource extends AnswersSource {
	
	private Answer.SortOrder sortOrder;
	
	public AnswersSortOrderSource(long questionId, Answer.SortOrder sortOrder) {
		super(questionId);
		this.sortOrder = sortOrder;
	}
	
	@Override
	public AnswerApiQuery customizeQuery(AnswerApiQuery query) {
		return query.withSort(sortOrder);
	}

}

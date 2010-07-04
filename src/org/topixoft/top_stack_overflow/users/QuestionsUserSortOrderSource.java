package org.topixoft.top_stack_overflow.users;

import org.topixoft.top_stack_overflow.questions.QuestionsSortOrderSource;

import com.google.code.stackexchange.client.query.QuestionApiQuery;
import com.google.code.stackexchange.schema.Question;

@SuppressWarnings("serial")
public class QuestionsUserSortOrderSource extends QuestionsSortOrderSource {
	
	private long userId;
	
	public QuestionsUserSortOrderSource(long userId) {
		super(Question.SortOrder.VOTES);
		this.userId = userId;
	}
	
	@Override
	public QuestionApiQuery customizeQuery(QuestionApiQuery query) {
		return super.customizeQuery(query).withUserIds(userId);
	}

}

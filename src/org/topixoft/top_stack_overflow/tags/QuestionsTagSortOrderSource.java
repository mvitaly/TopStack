package org.topixoft.top_stack_overflow.tags;

import org.topixoft.top_stack_overflow.questions.QuestionsSortOrderSource;

import com.google.code.stackexchange.client.query.QuestionApiQuery;
import com.google.code.stackexchange.schema.Question;

@SuppressWarnings("serial")
public class QuestionsTagSortOrderSource extends QuestionsSortOrderSource {
	
	private String tag;
	
	public QuestionsTagSortOrderSource(String tag) {
		super(Question.SortOrder.VOTES);
		this.tag = tag;
	}
	
	@Override
	public QuestionApiQuery customizeQuery(QuestionApiQuery query) {
		return super.customizeQuery(query).withTags(tag);
	}

}

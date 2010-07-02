package org.topixoft.top_stack_overflow.answers;

import java.util.EnumSet;
import java.util.List;

import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.TopStackOverflowActivity;

import com.google.code.stackexchange.client.query.AnswerApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.schema.Answer;
import com.google.code.stackexchange.schema.FilterOption;
import com.google.code.stackexchange.schema.Paging;

@SuppressWarnings("serial")
public abstract class AnswersSource implements PagableSource<Answer, AnswerApiQuery> {
	
	long questionId;
	
	public AnswersSource(long questionId) {
		this.questionId = questionId;
	}
	
	@Override
	public List<Answer> getItems(int pageNumber, int pageSize) {
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance(TopStackOverflowActivity.STACK_OVERFLOW_API_KEY);
		AnswerApiQuery query = queryFactory.newAnswerApiQuery();
	    final List<Answer> answers = customizeQuery(query)
	    		.withFetchOptions(
	    				EnumSet.of(FilterOption.INCLUDE_BODY,
	    						FilterOption.INCLUDE_COMMENTS)
	    				)
	    		.withQuestionIds(questionId)
	    		.withPaging(new Paging(pageNumber, pageSize))
	    		.listByQuestions();
	    
	    return answers;
	}

}

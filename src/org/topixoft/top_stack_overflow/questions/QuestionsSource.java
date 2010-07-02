package org.topixoft.top_stack_overflow.questions;

import java.util.List;

import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.TopStackOverflowActivity;

import com.google.code.stackexchange.client.query.QuestionApiQuery;
import com.google.code.stackexchange.client.query.SearchApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;

@SuppressWarnings("serial")
public abstract class QuestionsSource implements PagableSource<Question, QuestionApiQuery> {

	@Override
	public List<Question> getItems(int pageNumber, int pageSize) {
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance(TopStackOverflowActivity.STACK_OVERFLOW_API_KEY);
		QuestionApiQuery query = queryFactory.newQuestionApiQuery();
	    final List<Question> questions = customizeQuery(query)
	    		.withPaging(new Paging(pageNumber, pageSize))
	    		.list();
	    
	    return questions;
	}

}

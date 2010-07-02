package org.topixoft.top_stack_overflow.questions;

import java.util.List;

import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.TopStackOverflowActivity;

import com.google.code.stackexchange.client.query.SearchApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;

@SuppressWarnings("serial")
public class QuestionsSearchSource implements PagableSource<Question, SearchApiQuery> {
	
	private String searchString;
	
	private Question.SortOrder sortOrder;
	
	public QuestionsSearchSource(String searchString, Question.SortOrder sortOrder) {
		this.sortOrder = sortOrder;
		this.searchString = searchString;
	}
	
	@Override
	public List<Question> getItems(int pageNumber, int pageSize) {
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance(TopStackOverflowActivity.STACK_OVERFLOW_API_KEY);
		SearchApiQuery query = queryFactory.newSearchApiQuery();
	    final List<Question> questions = customizeQuery(query)
	    		.withPaging(new Paging(pageNumber, pageSize))
	    		.list();
	    
	    return questions;
	}
	
	@Override
	public SearchApiQuery customizeQuery(SearchApiQuery query) {
		if (searchString != null)
			query = query.withInTitle(searchString);
		
		if (sortOrder != null)
			query = query.withSort(sortOrder);
		
		return query;
	}
	
}

package org.topixoft.top_stack_overflow.tags;

import java.util.List;

import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.TopStackOverflowActivity;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.client.query.TagApiQuery;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Tag;

@SuppressWarnings("serial")
public abstract class TagsSource implements PagableSource<Tag, TagApiQuery> {

	@Override
	public List<Tag> getItems(int pageNumber, int pageSize) {
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance(TopStackOverflowActivity.STACK_OVERFLOW_API_KEY);
	    TagApiQuery query = queryFactory.newTagApiQuery();
	    final List<Tag> tags = customizeQuery(query)
	    		.withPaging(new Paging(pageNumber, pageSize))
	    		.list();
	    
	    return tags;
	}
}

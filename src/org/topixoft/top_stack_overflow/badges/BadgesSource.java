package org.topixoft.top_stack_overflow.badges;

import java.util.List;

import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.TopStackOverflowActivity;

import com.google.code.stackexchange.client.query.BadgeApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.schema.Badge;

@SuppressWarnings("serial")
public abstract class BadgesSource implements PagableSource<Badge, BadgeApiQuery> {

	@Override
	public List<Badge> getItems(int pageNumber, int pageSize) {
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance(TopStackOverflowActivity.STACK_OVERFLOW_API_KEY);
	    BadgeApiQuery query = queryFactory.newBadgeApiQuery();
	    final List<Badge> tags = getItems(customizeQuery(query));
	    
	    return tags;
	}
	
	@Override
	public BadgeApiQuery customizeQuery(BadgeApiQuery query) {
		return query;
	}

	protected abstract List<Badge> getItems(BadgeApiQuery query);
	
}

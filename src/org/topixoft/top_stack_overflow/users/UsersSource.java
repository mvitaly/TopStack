package org.topixoft.top_stack_overflow.users;

import java.util.List;

import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.TopStackOverflowActivity;

import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.client.query.UserApiQuery;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.User;

@SuppressWarnings("serial")
public abstract class UsersSource implements PagableSource<User, UserApiQuery> {

	@Override
	public List<User> getItems(int pageNumber, int pageSize) {
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance(TopStackOverflowActivity.STACK_OVERFLOW_API_KEY);
		UserApiQuery query = queryFactory.newUserApiQuery();
	    final List<User> tags = customizeQuery(query)
	    		.withPaging(new Paging(pageNumber, pageSize))
	    		.list();
	    
	    return tags;
	}
}

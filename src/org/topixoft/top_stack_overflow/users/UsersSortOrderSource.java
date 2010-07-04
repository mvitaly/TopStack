package org.topixoft.top_stack_overflow.users;

import com.google.code.stackexchange.client.query.UserApiQuery;
import com.google.code.stackexchange.schema.User;

@SuppressWarnings("serial")
public class UsersSortOrderSource extends UsersSource {
	
	private User.SortOrder sortOrder;
	
	public UsersSortOrderSource(User.SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Override
	public UserApiQuery customizeQuery(UserApiQuery query) {
		return query.withSort(sortOrder);
	}

}

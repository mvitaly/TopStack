package org.topixoft.top_stack_overflow.badges;

import java.util.List;

import org.topixoft.top_stack_overflow.users.UsersSource;

import com.google.code.stackexchange.client.query.UserApiQuery;
import com.google.code.stackexchange.schema.User;

@SuppressWarnings("serial")
public class UsersBadgeSortOrderSource extends UsersSource {
	
	private long badgeId;
	
	public UsersBadgeSortOrderSource(long badgeId) {
		this.badgeId = badgeId;
	}
	
	@Override
	public UserApiQuery customizeQuery(UserApiQuery query) {
		return query.withBadgeIds(badgeId);
	}
	
	@Override
	protected List<User> getItems(UserApiQuery query) {
		return query.listByBadge();
	}
	
}

package org.topixoft.top_stack_overflow.badges;

import java.util.List;

import com.google.code.stackexchange.client.query.BadgeApiQuery;
import com.google.code.stackexchange.client.query.TagApiQuery;
import com.google.code.stackexchange.schema.Badge;
import com.google.code.stackexchange.schema.Tag;

@SuppressWarnings("serial")
public class BadgesTagBasedSource extends BadgesSource {
	
	private boolean tagBased;
	
	public BadgesTagBasedSource(boolean tagBased) {
		this.tagBased = tagBased;
	}
	
	@Override
	protected List<Badge> getItems(BadgeApiQuery query) {
		if (!tagBased) {
			return query.listByName();
		} else {
			return query.listByTags();
		}
	}

}

package org.topixoft.top_stack_overflow.tags;

import com.google.code.stackexchange.client.query.TagApiQuery;
import com.google.code.stackexchange.schema.Tag;

@SuppressWarnings("serial")
public class TagsSortOrderSource extends TagsSource {
	
	private Tag.SortOrder sortOrder;
	
	public TagsSortOrderSource(Tag.SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Override
	public TagApiQuery customizeQuery(TagApiQuery query) {
		return query.withSort(sortOrder);
	}

}

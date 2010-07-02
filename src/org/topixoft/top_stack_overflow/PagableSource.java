package org.topixoft.top_stack_overflow;

import java.io.Serializable;
import java.util.List;

public interface PagableSource<I, Q> extends Serializable {
	
	public abstract List<I> getItems(int pageNumber, int pageSize);
	
	abstract Q customizeQuery(Q query);
	
}

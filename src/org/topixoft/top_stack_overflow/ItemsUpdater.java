package org.topixoft.top_stack_overflow;

import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

public interface ItemsUpdater<I> {
	
	void updateListAdapter(PagableListAdapter<I> listAdapter);
	
}

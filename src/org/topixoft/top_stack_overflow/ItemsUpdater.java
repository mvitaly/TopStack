package org.topixoft.top_stack_overflow;

public interface ItemsUpdater<I> {
	
	void updateListAdapter(PagableListAdapter<I> listAdapter);
	
}

package org.topixoft.top_stack_overflow;

import org.topixoft.top_stack_overflow.adapters.LoadingAdapter;
import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

public abstract class AbstractListActivity<I> extends Activity implements ItemsUpdater<I>, AdapterView.OnItemClickListener {

	private final String TAG = this.getClass().getSimpleName();
	
	protected ListView listView;
	
	protected PagableListAdapter<I> listAdapter;
	
	protected abstract int getContentViewId();
	
	protected abstract int getListViewId();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(getContentViewId());
		listView = (ListView) findViewById(getListViewId());
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (parent.getAdapter() instanceof LoadingAdapter)
					return;
				
				if (id >= 0) {
					AbstractListActivity.this.onItemClick(parent, view, position, id);
				} else {
					ProgressBar progressbar = (ProgressBar) view.findViewById(R.id.progressbarLoadingLoadNext);
					progressbar.setVisibility(View.VISIBLE);
					
					listAdapter.loadNextPage();
				}
			}
		});
		listView.setAdapter(new LoadingAdapter(this));
	}
	
	@Override
	public void updateListAdapter(PagableListAdapter<I> listAdapter) {
		if (this.listAdapter == null) {
			this.listAdapter = listAdapter;
			listView.setAdapter(listAdapter);
		} else {
			listAdapter.notifyDataSetChanged();
		}
	}

}

package org.topixoft.top_stack_overflow.adapters;

import java.util.ArrayList;
import java.util.List;

import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.PagableSource;
import org.topixoft.top_stack_overflow.R;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public abstract class PagableListAdapter<I> extends BaseAdapter {
	
	private final String TAG = this.getClass().getSimpleName();
	
	protected Activity activity;
	protected LayoutInflater inflater;
	private PagableSource<I, ?> source;
	private ItemsUpdater<I> itemsUpdater;
	
	protected int pageNumber = 1;
	protected int pageSize = 10;
	
	boolean showNextPageItem;
	final List<I> items = new ArrayList<I>();
	
	public PagableListAdapter(Activity activity, PagableSource<I, ?> source, ItemsUpdater<I> itemsUpdater) {
		this.activity = activity;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.source = source;
		this.itemsUpdater = itemsUpdater;
		
		loadNextPage();
	}
	
	public void loadNextPage() {
		new Thread(new PopulateListRunnable()).start();
	}
	
	@Override
	public final int getCount() {
		return items.size() + (showNextPageItem ? 1 : 0);
	}

	@Override
	public final I getItem(int position) {
		if (showNextPageItem && position == items.size())
			return null;
		
		return items.get(position);
	}

	public abstract long getItemIdInternal(I item);
	
	@Override
	public final long getItemId(int position) {
		if (showNextPageItem && position == items.size())
			return -1;
		
		return getItemIdInternal(getItem(position));
	}

	public void enrichItemsBeforeView(List<I> newItems) {
	}
	
	public abstract View getViewInternal(int position, View convertView, ViewGroup parent);

	public abstract String getItemsString();
	
	@Override
	public final View getView(int position, View convertView, ViewGroup parent) {
		View view;
		
		if (showNextPageItem && position == items.size()) {
			if (convertView == null || convertView.getId() != R.id.layoutLoadingLoadNext) {
				view = inflater.inflate(R.layout.question_loading_next_page_row, parent, false);
			} else {
				view = convertView;
			}
			
			TextView textview = (TextView) view.findViewById(R.id.textviewLoadingLoadNext);
			textview.setText("Load next " + getItemsString() + "...");
		} else {
			view = getViewInternal(position, convertView, parent);
		}
		
        return view;

	}
	
	protected String beautifyAndStringify(long num) {
		if (num >= 100000)
			return (num / 1000) + "k";
		else if (num >= 1000)
			return (num / 1000) + "k";
		else
			return "" + num;
	}

	public class PopulateListRunnable implements Runnable {
		@Override
		public void run() {
			Log.d(TAG, "Retrieveing questions list");
			try {
				List<I> newItems = source.getItems(pageNumber, pageSize);
				enrichItemsBeforeView(newItems);
				items.addAll(newItems);
				
				showNextPageItem = (newItems.size() == pageSize);
				pageNumber++;
				
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						itemsUpdater.updateListAdapter(PagableListAdapter.this);
					}
				});
			} catch (Exception e) {
				Log.e(TAG, "Error retrieveing questions list", e);
			}
		}
	}

}

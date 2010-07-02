package org.topixoft.top_stack_overflow.adapters;

import org.topixoft.top_stack_overflow.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

public class LoadingAdapter extends BaseAdapter {
	
	LayoutInflater inflater;
	
	public LoadingAdapter(Context context) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.question_loading_next_page_row, parent, false);
		ProgressBar progressbar = (ProgressBar) view.findViewById(R.id.progressbarLoadingLoadNext);
		progressbar.setVisibility(View.VISIBLE);
		return view;
	}
	
}

package org.topixoft.top_stack_overflow.tags;

import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.code.stackexchange.schema.Tag;

public class TagsListAdapter extends PagableListAdapter<Tag> {

	Resources resources;
	
	public TagsListAdapter(Activity activity, TagsSource source, ItemsUpdater<Tag> itemsUpdater) {
		super(activity, source, itemsUpdater);
		resources = activity.getResources();
	}

	@Override
	public View getViewInternal(int position, View convertView, ViewGroup parent) {
		View view;
		
		if (convertView == null || convertView.getId() != R.id.layoutTagRow) {
            view = inflater.inflate(R.layout.tag_row, parent, false);
        } else {
            view = convertView;
        }
		
		populateView(getItem(position), view);
		
        return view;
	}

	private void populateView(Tag tag, View view) {
		TextView textviewTag = (TextView) view.findViewById(R.id.textviewTag);
		TextView textviewTagValue = (TextView) view.findViewById(R.id.textviewTagValue);
		
		textviewTag.setText(tag.getName());
		textviewTagValue.setText("" + tag.getCount());
	}
	
	@Override
	public String getItemsString() {
		return "tags";
	}
	
	@Override
	public long getItemIdInternal(Tag item) {
		return 0;
	}
	
}

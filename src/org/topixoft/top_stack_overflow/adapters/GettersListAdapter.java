package org.topixoft.top_stack_overflow.adapters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class GettersListAdapter implements ListAdapter {

	SimpleAdapter adapter;
	List<?> data;
	
	public GettersListAdapter(Context context,
			List<?> data, int resource, String[] from,
			int[] to) {
		this.data = data;
		
		List<Map<String, ?>> lst = new ArrayList<Map<String,?>>(data.size());
		for (Object obj : data) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (Method m : obj.getClass().getMethods()) {
				String methodName = m.getName();
				if (methodName.startsWith("get") && m.getParameterTypes().length == 0) {
					String key = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
					if (keyNeeded(key, from)) {
						try {
							map.put(key, m.invoke(obj));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			lst.add(map);
		}
		
		adapter = new SimpleAdapter(context, lst, resource, from, to);
	}

	private boolean keyNeeded(String key, String[] from) {
		for (String prop : from) {
			if (prop.equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return adapter.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int position) {
		return adapter.isEnabled(position);
	}

	@Override
	public int getCount() {
		return adapter.getCount();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return adapter.getItemId(position);
	}

	@Override
	public int getItemViewType(int position) {
		return adapter.getItemViewType(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return adapter.getView(position, convertView, parent);
	}

	@Override
	public int getViewTypeCount() {
		return adapter.getViewTypeCount();
	}

	@Override
	public boolean hasStableIds() {
		return adapter.hasStableIds();
	}

	@Override
	public boolean isEmpty() {
		return adapter.isEmpty();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		adapter.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		adapter.unregisterDataSetObserver(observer);
	}
	
}

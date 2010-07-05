package org.topixoft.top_stack_overflow.badges;

import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.code.stackexchange.schema.Badge;

public class BadgesListAdapter extends PagableListAdapter<Badge> {

	Resources resources;
	
	public BadgesListAdapter(Activity activity, BadgesSource source, ItemsUpdater<Badge> itemsUpdater) {
		super(activity, source, itemsUpdater);
		resources = activity.getResources();
		
		pageSize = -1;
	}

	@Override
	public View getViewInternal(int position, View convertView, ViewGroup parent) {
		View view;
		
		if (convertView == null || convertView.getId() != R.id.layoutBadgeRow) {
            view = inflater.inflate(R.layout.badge_row, parent, false);
        } else {
            view = convertView;
        }
		
		populateView(getItem(position), view);
		
        return view;
	}

	private void populateView(Badge badge, View view) {
		View layoutBadge = view.findViewById(R.id.layoutBadge);
		TextView textviewTag = (TextView) view.findViewById(R.id.textviewBadge);
		ImageView imageviewBagdeType = (ImageView) view.findViewById(R.id.imageviewBadgeType);
		TextView textviewTagValue = (TextView) view.findViewById(R.id.textviewBadgeValue);
		TextView textviewBadgeDescription = (TextView) view.findViewById(R.id.textviewBadgeDescription);
		
		textviewTag.setText(badge.getName());
		if (!badge.isTagBased()) {
			layoutBadge.setBackgroundResource(R.drawable.badge_bg_normal_shape);
			textviewTag.setTextColor(Color.parseColor("#FFFFFF"));
		} else {
			layoutBadge.setBackgroundResource(R.drawable.badge_bg_tagbased_shape);
			textviewTag.setTextColor(Color.parseColor("#333333"));
		}
		
		switch (badge.getRank()) {
			case GOLD:
				imageviewBagdeType.setImageResource(R.drawable.badge_gold);
				break;
			case SILVER:
				imageviewBagdeType.setImageResource(R.drawable.badge_silver);
				break;
			case BRONZE:
				imageviewBagdeType.setImageResource(R.drawable.badge_bronze);
				break;
		}
		
		textviewTagValue.setText("" + badge.getAwardCount());
		textviewBadgeDescription.setText(badge.getDescription());
		
	}
	
	@Override
	public String getItemsString() {
		return "badges";
	}
	
	@Override
	public long getItemIdInternal(Badge item) {
		return item.getBadgeId();
	}
	
}

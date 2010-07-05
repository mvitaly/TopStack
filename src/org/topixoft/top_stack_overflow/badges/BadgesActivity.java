package org.topixoft.top_stack_overflow.badges;

import org.topixoft.top_stack_overflow.AbstractListActivity;
import org.topixoft.top_stack_overflow.users.UsersActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.code.stackexchange.schema.Badge;

public class BadgesActivity extends AbstractListActivity<Badge> implements AdapterView.OnItemClickListener {

	public static final String BADGES_SOURCE_EXTRA = "badgesSource";
	
	BadgesSource source = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			source = (BadgesSource) extras.get(BADGES_SOURCE_EXTRA);
		}
		
		super.onCreate(savedInstanceState);
		
		new BadgesListAdapter(this, source, this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Badge badge = (Badge) parent.getItemAtPosition(position);
	    Intent intent = new Intent().setClass(this, UsersActivity.class);
	    intent.putExtra(UsersActivity.USERS_SOURCE_EXTRA, new UsersBadgeSortOrderSource(badge.getBadgeId()));
	    startActivity(intent);
	}

}

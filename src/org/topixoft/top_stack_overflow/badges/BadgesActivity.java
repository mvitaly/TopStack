package org.topixoft.top_stack_overflow.badges;

import org.topixoft.top_stack_overflow.AbstractListActivity;

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
//		Tag tag = (Tag) parent.getItemAtPosition(position);
//	    Intent intent = new Intent().setClass(this, QuestionsActivity.class);
//	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsTagSortOrderSource(tag.getName()));
//	    startActivity(intent);
	}

}

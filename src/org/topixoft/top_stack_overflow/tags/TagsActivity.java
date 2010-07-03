package org.topixoft.top_stack_overflow.tags;

import org.topixoft.top_stack_overflow.AbstractListActivity;
import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.questions.QuestionsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.code.stackexchange.schema.Tag;

public class TagsActivity extends AbstractListActivity<Tag> implements AdapterView.OnItemClickListener {

	public static final String TAGS_SOURCE_EXTRA = "tagsSource";
	
	TagsSource source = null;
	
	@Override
	protected int getContentViewId() {
		return R.layout.tags;
	}

	@Override
	protected int getListViewId() {
		return R.id.listviewTags;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			source = (TagsSource) extras.get(TAGS_SOURCE_EXTRA);
		}
		
		super.onCreate(savedInstanceState);
		
		new TagsListAdapter(this, source, this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Tag tag = (Tag) parent.getItemAtPosition(position);
	    Intent intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsTagSortOrderSource(tag.getName()));
	    startActivity(intent);
	}

//	@Override
//	protected void updateListItems(ListView listView, List<Tag> items) {
//		listView.setAdapter(new GettersListAdapter(
//				this,
//				items,
//				R.layout.tag_row,
//				new String[] {
//					"name",
//					"count"
//				},
//				new int[] {
//					R.id.textviewTag,
//					R.id.textviewTagValue
//				}));
//	}
//
//	protected List<Tag> getListItems() {
//		if (TopStackOverflowActivity.DEBUG_MODE) {
//			List<Tag> lst = new ArrayList<Tag>();
//			Tag t;
//			
//			t = new Tag();
//			t.setName("test1");
//			t.setCount(10);
//			lst.add(t);
//			
//			t = new Tag();
//			t.setName("test2");
//			t.setCount(10);
//			lst.add(t);
//			
//			return lst;
//		}
//		
//	    return source.getItems(1, 10);
//	}

}

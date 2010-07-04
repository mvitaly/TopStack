package org.topixoft.top_stack_overflow.users;

import org.topixoft.top_stack_overflow.AbstractListActivity;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.questions.QuestionsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.code.stackexchange.schema.Tag;
import com.google.code.stackexchange.schema.User;

public class UsersActivity extends AbstractListActivity<User> implements AdapterView.OnItemClickListener {

	public static final String USERS_SOURCE_EXTRA = "usersSource";
	
	UsersSource source = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			source = (UsersSource) extras.get(USERS_SOURCE_EXTRA);
		}
		
		super.onCreate(savedInstanceState);
		
		new UsersListAdapter(this, source, this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		User user = (User) parent.getItemAtPosition(position);
	    Intent intent = new Intent().setClass(this, QuestionsActivity.class);
	    intent.putExtra(QuestionsActivity.QUESTIONS_SOURCE_EXTRA, new QuestionsUserSortOrderSource(user.getUserId()));
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

package org.topixoft.top_stack_overflow.users;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.topixoft.top_stack_overflow.ItemsUpdater;
import org.topixoft.top_stack_overflow.R;
import org.topixoft.top_stack_overflow.adapters.PagableListAdapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.code.stackexchange.schema.BadgeRank;
import com.google.code.stackexchange.schema.User;

public class UsersListAdapter extends PagableListAdapter<User> {

	Resources resources;
	Map<User, Bitmap> userImages = new HashMap<User, Bitmap>();

	public UsersListAdapter(Activity activity, UsersSource source,
			ItemsUpdater<User> itemsUpdater) {
		super(activity, source, itemsUpdater);
		resources = activity.getResources();
	}

	private InputStream openHttpConnection(String strURL) throws IOException {
		InputStream inputStream = null;
		URL url = new URL(strURL);
		URLConnection conn = url.openConnection();

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpConn.getInputStream();
			}
		} catch (Exception ex) {
		}
		return inputStream;
	}

	private Bitmap loadImageFromUrl(String url, BitmapFactory.Options options) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = openHttpConnection(url);
			bitmap = BitmapFactory.decodeStream(in, null, options);
			in.close();
		} catch (IOException e1) {
		}
		return bitmap;
	}

	@Override
	public void enrichItemsBeforeView(List<User> newItems) {
		for (User user : newItems) {
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();
			bmOptions.inSampleSize = 1;
			Bitmap bm = loadImageFromUrl(
					"http://www.gravatar.com/avatar/" + user.getEmailHash() + "?s=32&d=identicon&r=PG",
					bmOptions);
			if (bm != null) {
				userImages.put(user, bm);
			}
		}
	}

	@Override
	public View getViewInternal(int position, View convertView, ViewGroup parent) {
		View view;

		if (convertView == null || convertView.getId() != R.id.layoutUserRow) {
			view = inflater.inflate(R.layout.user_row, parent, false);
		} else {
			view = convertView;
		}

		populateView(getItem(position), view);

		return view;
	}

	private void populateView(User user, View view) {
		TextView textviewUserName = (TextView) view.findViewById(R.id.textviewUserName);
		TextView textviewUserReputation = (TextView) view.findViewById(R.id.textviewUserReputation);
		ImageView imageviewUserImage = (ImageView) view.findViewById(R.id.imageviewUserImage);
		View layoutUserGoldBadges = view.findViewById(R.id.layoutUserGoldBadges);
		View layoutUserSilverBadges = view.findViewById(R.id.layoutUserSilverBadges);
		View layoutUserBronzeBadges = view.findViewById(R.id.layoutUserBronzeBadges);
		TextView textviewUserGoldBadges = (TextView) view.findViewById(R.id.textviewUserGoldBadges);
		TextView textviewUserSilverBadges = (TextView) view.findViewById(R.id.textviewUserSilverBadges);
		TextView textviewUserBronzeBadges = (TextView) view.findViewById(R.id.textviewUserBronzeBadges);

		textviewUserName.setText(user.getDisplayName());
		Bitmap userImage = userImages.get(user);
		if (userImage != null) {
			imageviewUserImage.setImageBitmap(userImage);
		}
		
		long goldBadges = user.getBadgeCounts().get(BadgeRank.GOLD);
		long silverBadges = user.getBadgeCounts().get(BadgeRank.SILVER);
		long bronzeBadges = user.getBadgeCounts().get(BadgeRank.BRONZE);
		
		if (goldBadges > 0) {
			layoutUserGoldBadges.setVisibility(View.VISIBLE);
			textviewUserGoldBadges.setText("" + goldBadges);
		}
		
		if (silverBadges > 0) {
			layoutUserSilverBadges.setVisibility(View.VISIBLE);
			textviewUserSilverBadges.setText("" + silverBadges);
		}
		
		if (bronzeBadges > 0) {
			layoutUserBronzeBadges.setVisibility(View.VISIBLE);
			textviewUserBronzeBadges.setText("" + bronzeBadges);
		}
		
		textviewUserReputation.setText(beautifyAndStringify(user
				.getReputation()));
	}

	@Override
	public String getItemsString() {
		return "users";
	}

	@Override
	public long getItemIdInternal(User item) {
		return item.getUserId();
	}

}

package org.brethren.taranim;

import org.brethren.taranim.settings.SettingsActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.MenuItem;

import org.brethren.taranim.R;


public class MenuHelper {
	static AlertDialog alertDialog;

	public static boolean openActivityFromMenuItem(Context context,
			MenuItem item) {
		Intent intent;
		@SuppressWarnings("unchecked")
		Class<? extends Activity> requestingClass = (Class<? extends Activity>) context
				.getClass();
		switch (item.getItemId()) {
		case R.id.settings:
			if (requestingClass != SettingsActivity.class) {
				intent = new Intent(context, SettingsActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(intent);
				return true;
			}
			break;


		case R.id.search:
			if (requestingClass != SearchActivity.class) {
				intent = new Intent(context, SearchActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(intent);
				return true;
			}
			break;

		case R.id.about:
			alertDialog = new AlertDialog.Builder(context)
					.setTitle(R.string.app_name)
					.setMessage(R.string.about_text)
					.setPositiveButton(R.string.ok, new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							alertDialog.dismiss();
							alertDialog = null;
						}
					}).show();
			break;

		case R.id.home:
			if (requestingClass != SongActivity.class) {
				intent = new Intent(context, SongActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(intent);
				return true;
			}

			break;

			case R.id.help:
				if (requestingClass != HelpActivity.class) {
					intent = new Intent(context, HelpActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(intent);
					return true;
				}

				break;

		}

		return false;
	}
}
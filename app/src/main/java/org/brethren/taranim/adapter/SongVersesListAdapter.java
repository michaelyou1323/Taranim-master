package org.brethren.taranim.adapter;

import java.util.ArrayList;

import org.brethren.taranim.model.SongVerses;
import org.brethren.taranim.view.SongVersesListItem;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.brethren.taranim.R;

public class SongVersesListAdapter extends BaseAdapter{
	
	private final ArrayList<SongVerses> verses;
	private Context context;
	
	public SongVersesListAdapter(ArrayList<SongVerses> verses, Context context) {
		super();
		this.verses = verses;
		this.context = context;
	}
	
	public Context getContext() {
		return context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return verses.size();
	}

	@Override
	public SongVerses getItem(int position) {
		return (null == verses) ? null : verses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SongVersesListItem svli;
		if (null == convertView) {
			svli = (SongVersesListItem) View.inflate(context, R.layout.song_verses_list_item, null);
		} else {
			svli = (SongVersesListItem) convertView;
		}

		if (position % 2 == 1)
			svli.setBackgroundColor(Color.WHITE);
		else
			svli.setBackgroundColor(Color.LTGRAY);

		svli.setVerses(verses.get(position));
		return svli;
	}
}

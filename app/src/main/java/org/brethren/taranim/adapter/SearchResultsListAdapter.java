package org.brethren.taranim.adapter;

import java.util.ArrayList;

import org.brethren.taranim.model.SongVerses;
import org.brethren.taranim.view.SearchResultsListItem;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.brethren.taranim.R;

public class SearchResultsListAdapter extends BaseAdapter{
	
	private final ArrayList<SongVerses> verses;
	private Context context;
	
	public SearchResultsListAdapter(ArrayList<SongVerses> verses, Context context) {
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
		SearchResultsListItem srli;
		if (null == convertView) {
			srli = (SearchResultsListItem) View.inflate(context, R.layout.search_results_list_item, null);
		} else {
			srli = (SearchResultsListItem) convertView;
		}

		if (position % 2 == 1)
			srli.setBackgroundColor(Color.WHITE);
		else
			srli.setBackgroundColor(Color.LTGRAY);

		srli.setSongVerses(verses.get(position));
		return srli;
	}
}

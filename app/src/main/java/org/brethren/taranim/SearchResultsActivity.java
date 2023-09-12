package org.brethren.taranim;

import org.brethren.taranim.adapter.SearchResultsListAdapter;
import org.brethren.taranim.model.Song;
import org.brethren.taranim.model.SongVerses;
import org.brethren.taranim.settings.SettingsHelper;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.brethren.taranim.R;


public class SearchResultsActivity extends ListActivity {
	
	private SettingsHelper settingsHelper;
	private Typeface tf;
	private TextView searchResultsTitle;
	private SearchResultsListAdapter adapter;
	private TaranimApplication app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results_activity);
		settingsHelper = new SettingsHelper(SearchResultsActivity.this);
		tf = Typeface.createFromAsset(this.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");
		searchResultsTitle = (TextView)findViewById(R.id.search_results_title);
		searchResultsTitle.setTypeface(tf);
		searchResultsTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
		app = (TaranimApplication) getApplication();
		Intent intent = getIntent();
		String searchText = (String)intent.getSerializableExtra("searchText");
		adapter = new SearchResultsListAdapter(app.searchVersesBy(searchText), this);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		SongVerses songVerse = adapter.getItem(position);
		Song song = app.getSongById(songVerse.getSongId());
		Intent intent = new Intent(SearchResultsActivity.this, SongVersesActivity.class);
		intent.putExtra("song", song);
		startActivity(intent);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuHelper.openActivityFromMenuItem(this, item);
	}

}

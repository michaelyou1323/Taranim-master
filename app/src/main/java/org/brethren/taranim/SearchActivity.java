package org.brethren.taranim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;



import org.brethren.taranim.R;

public class SearchActivity extends Activity {
	
	private EditText searchText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		setUpViews();
	}

	private void setUpViews() {
		searchText = (EditText) findViewById(R.id.search_text);
	}
	
	public void searchButtonClicked(View view) {
		Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
		intent.putExtra("searchText", searchText.getText().toString());
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

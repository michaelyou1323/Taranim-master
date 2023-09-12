package org.brethren.taranim.settings;

import org.brethren.taranim.MenuHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import org.brethren.taranim.R;

public class SettingsActivity extends Activity{
	
	private Spinner fontsSpinner;
	private SettingsHelper settingsHelper;
	private SeekBar fontSizeSeekBar;
	private int fontSize;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		setUpViews();
		settingsHelper = new SettingsHelper(SettingsActivity.this);
		setUpViews();
		loadSettings();
	}
	private void setUpViews() {
		fontsSpinner = (Spinner) findViewById(R.id.fonts_spinner);
		fontSizeSeekBar = (SeekBar) findViewById(R.id.font_size_seekbar);
		
		fontSizeSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				fontSize = progress;
				
			}
		});
	}
	
	private void loadSettings() {
		fontsSpinner.setSelection(settingsHelper.getFontPositon());
		fontSizeSeekBar.setProgress(settingsHelper.getFontSize());
	}

	public void saveSettings(View view){
		String fontName = fontsSpinner.getSelectedItem().toString();
		int position = fontsSpinner.getSelectedItemPosition();
		settingsHelper.saveSeetings(fontName,position,fontSize);
		Toast.makeText(this, R.string.settings_saved, Toast.LENGTH_LONG).show();
		finish();
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

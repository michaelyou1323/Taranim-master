package org.brethren.taranim.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SettingsHelper {
	private static final String APPLICATION_PREFERENCES = "app_prefs";
	private static final String FONT_NAME = "font_name";
	private static final String FONT_POSITION = "font_position";
	private static final String FONT_SIZE = "font_size";
	private final SharedPreferences prefs;

	public SettingsHelper(Context context) {
		prefs = context.getSharedPreferences(APPLICATION_PREFERENCES, Context.MODE_PRIVATE);
	}
	public void saveSeetings(String fontName, int position, int fontSize) {
		Editor editor = prefs.edit();
	    editor.putString(FONT_NAME, fontName);
	    editor.putInt(FONT_POSITION, position);
	    editor.putInt(FONT_SIZE, fontSize);
	    editor.apply();
	}
	public String getFontName() {
		return prefs.getString(FONT_NAME, "Roboto-Regular");
	}
	public int getFontPositon(){
		return prefs.getInt(FONT_POSITION, 4);
	}
	public int getFontSize(){
		return prefs.getInt(FONT_SIZE, 30);
	}
	

}

package org.brethren.taranim.view;

import org.brethren.taranim.model.SongVerses;
import org.brethren.taranim.settings.SettingsHelper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.brethren.taranim.R;


public class SearchResultsListItem extends LinearLayout{
	
	private SongVerses verses;
	private TextView songNumber;
	private TextView verseNumber;
	private TextView verseText;
	private Typeface tf;
	private SettingsHelper settingsHelper;
	private Context context;
	public SearchResultsListItem(Context context, AttributeSet attr) {
		super(context, attr);
		this.context = context;
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		settingsHelper = new SettingsHelper(context);
		tf = Typeface.createFromAsset(context.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");	
		songNumber = (TextView)findViewById(R.id.search_song_number);
		verseText = (TextView)findViewById(R.id.search_verse_text);
	    verseNumber = (TextView)findViewById(R.id.search_verse_number);
		
	    verseText.setTypeface(tf);
	    verseText.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
		songNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
		verseNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
	}

	public SongVerses getSongVerses() {
		return verses;
	}

	public void setSongVerses(SongVerses verses) {
		long songNumber = verses.getSongId();
		if (verses.getNumber()!= 0)
			verseNumber.setText(verses.getNumber() + ":" + songNumber);
		else
			verseNumber.setText(getResources().getString(R.string.karar) + ":" + songNumber);
		verseText.setText(verses.getVerseText().replace('/', '\n'));
		this.verses = verses;
	}


}

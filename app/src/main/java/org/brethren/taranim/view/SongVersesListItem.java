package org.brethren.taranim.view;

import org.brethren.taranim.model.SongVerses;
import org.brethren.taranim.settings.SettingsHelper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.brethren.taranim.R;


public class SongVersesListItem extends LinearLayout{
	
	private SongVerses verses;
	private TextView verseNumber;
	private TextView verseText;
	private Typeface tf;
	private SettingsHelper settingsHelper;
	private Context context;
	public SongVersesListItem(Context context, AttributeSet attr) {
		super(context, attr);	
		this.context = context;
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		settingsHelper = new SettingsHelper(context);
		tf = Typeface.createFromAsset(context.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");
		verseNumber = (TextView)findViewById(R.id.verse_number);
		verseText = (TextView)findViewById(R.id.verse_text);
		verseText.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
		verseNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
		verseText.setTypeface(tf);
		verseNumber.setTypeface(tf);
	}

	public SongVerses getVerses() {
		return verses;
	}

	public void setVerses(SongVerses verses) {
		if (verses.getNumber() != 0) {
			verseNumber.setText("" + verses.getNumber());
			verseText.setTextColor(Color.BLACK);
			verseNumber.setTextColor(Color.BLACK);
		}
		else {
			verseNumber.setText(getResources().getString(R.string.karar));
			verseNumber.setTextColor(Color.RED);
			verseText.setTextColor(Color.parseColor("#36562d"));
		}
		verseText.setText(verses.getVerseText().replace('/', '\n'));
		this.verses = verses;
	}
}

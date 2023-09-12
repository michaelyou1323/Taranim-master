package org.brethren.taranim.view;

import org.brethren.taranim.model.Song;
import org.brethren.taranim.settings.SettingsHelper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.brethren.taranim.R;


public class SongListItem extends LinearLayout{
	
	private Song song;
	private TextView songNumber;
	private TextView songTitle;
	private Typeface tf;
	private SettingsHelper settingsHelper;
	private Context context;
	public SongListItem(Context context, AttributeSet attr) {
		super(context, attr);
		this.context = context;
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		settingsHelper = new SettingsHelper(context);
		tf = Typeface.createFromAsset(context.getAssets(),"fonts/"+settingsHelper.getFontName()+".ttf");	
		songNumber = (TextView)findViewById(R.id.song_number);
		songTitle = (TextView)findViewById(R.id.song_title);
		songTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
		songNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP,settingsHelper.getFontSize());
		songTitle.setTypeface(tf);
		songNumber.setTypeface(tf);
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		songNumber.setText(""+song.getNumber());
		songTitle.setText(song.getTitle());
		this.song = song;
	}


}

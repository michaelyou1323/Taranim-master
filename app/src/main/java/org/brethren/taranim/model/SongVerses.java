package org.brethren.taranim.model;

public class SongVerses {
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	public long getSongId() {
		return songId;
	}
	public void setSongId(long songId) {
		this.songId = songId;
	}
	public String getVerseText() {
		return verseText;
	}
	public void setVerseText(String verseText) {
		this.verseText = verseText;
	}
	public String getVerseSearchText() {
		return verseSearchText;
	}
	public void setVerseSearchText(String verseSearchText) {
		this.verseSearchText = verseSearchText;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	private long id;
	private long songId;
	private String verseText;
	private String verseSearchText;
	private int number;
	
}

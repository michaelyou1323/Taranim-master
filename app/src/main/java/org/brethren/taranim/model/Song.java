package org.brethren.taranim.model;

import java.io.Serializable;

public class Song implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getTune() {
		return tune;
	}
	public void setTune(String tune) {
		this.tune = tune;
	}
	public String getMeter() {
		return meter;
	}
	public void setMeter(String meter) {
		this.meter = meter;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getChords() {
		return chords;
	}
	public void setChords(String chords) {
		this.chords = chords;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getNazm() {
		return nazm;
	}
	public void setNazm(String nazm) {
		this.nazm = nazm;
	}
	String nazm;
	
	long id;
	String title;
	int number;
	String author;
	String composer;
	String tune;
	String meter;
	String signature;
	String chords;
	
}

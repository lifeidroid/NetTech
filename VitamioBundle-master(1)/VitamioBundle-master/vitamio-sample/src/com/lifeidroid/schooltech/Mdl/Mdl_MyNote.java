package com.lifeidroid.schooltech.Mdl;

public class Mdl_MyNote {
	private int noteId;
	private String content;
	private String noteTime;
	private int collectedNum;
	public Mdl_MyNote(int noteId, String content, String noteTime,
			int collectedNum) {
		super();
		this.noteId = noteId;
		this.content = content;
		this.noteTime = noteTime;
		this.collectedNum = collectedNum;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNoteTime() {
		return noteTime;
	}
	public void setNoteTime(String noteTime) {
		this.noteTime = noteTime;
	}
	public int getCollectedNum() {
		return collectedNum;
	}
	public void setCollectedNum(int collectedNum) {
		this.collectedNum = collectedNum;
	}
	
}

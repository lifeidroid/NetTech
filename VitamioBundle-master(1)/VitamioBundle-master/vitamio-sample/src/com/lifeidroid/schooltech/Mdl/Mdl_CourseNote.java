package com.lifeidroid.schooltech.Mdl;

public class Mdl_CourseNote {
	private int noteId;
	private String studentEmail;
	private String studentNike;
	private String studentHead;
	private String content;
	private String noteTime;
	private int collectNum;
	public Mdl_CourseNote(int noteId, String studentEmail, String studentNike,
			String studentHead, String content, String noteTime, int collectNum) {
		super();
		this.noteId = noteId;
		this.studentEmail = studentEmail;
		this.studentNike = studentNike;
		this.studentHead = studentHead;
		this.content = content;
		this.noteTime = noteTime;
		this.collectNum = collectNum;
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentNike() {
		return studentNike;
	}
	public void setStudentNike(String studentNike) {
		this.studentNike = studentNike;
	}
	public String getStudentHead() {
		return studentHead;
	}
	public void setStudentHead(String studentHead) {
		this.studentHead = studentHead;
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
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	
}

package com.lifeidroid.schooltech.Mdl;


public class Mdl_CourseDiscuss {
	private int discussId;
	private String studentEmail;
	private String studentNike;
	private String studentHead;
	private String content;
	private String discussTime;
	private int replyNum;
	public Mdl_CourseDiscuss(int discussId, String studentEmail, String studentNike,
			String studentHead, String content, String discussTime, int replyNum) {
		super();
		this.discussId = discussId;
		this.studentEmail = studentEmail;
		this.studentNike = studentNike;
		this.studentHead = studentHead;
		this.content = content;
		this.discussTime = discussTime;
		this.replyNum = replyNum;
	}
	public int getDiscussId() {
		return discussId;
	}
	public void setDiscussId(int discussId) {
		this.discussId = discussId;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentId(String studentEmail) {
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
	public String getDiscussTime() {
		return discussTime;
	}
	public void setDiscussTime(String discussTime) {
		this.discussTime = discussTime;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	
}

package com.lifeidroid.schooltech.Mdl;

public class Mdl_CourseList {
	private int chapterId;
	private String chapterName;
	private String chaptertime;
	private String chapterUrl;

	public Mdl_CourseList(int chapterId, String chapterName, String chaptertime,String chapterUrl) {
		super();
		this.chapterId = chapterId;
		this.chapterName = chapterName;
		this.chaptertime = chaptertime;
		this.chapterUrl = chapterUrl;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChaptertime() {
		return chaptertime;
	}

	public void setChaptertime(String chaptertime) {
		this.chaptertime = chaptertime;
	}
	public String getChapterUrl() {
		return chapterUrl;
	}
	public void setChapterUrl(String chapterUrl) {
		this.chapterUrl = chapterUrl;
	}

}

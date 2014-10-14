package com.lifeidroid.schooltech.Mdl;

public class Mdl_Course {
	private int courseId;
	private String courseName;
	private String techName;
	private String courseLogo;
	private float garde;
	private String studentNum;
	public Mdl_Course(int courseId, String courseName, String techName,
			String courseLogo, float garde, String studentNum) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.techName = techName;
		this.courseLogo = courseLogo;
		this.garde = garde;
		this.studentNum = studentNum;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}
	public String getCourseLogo() {
		return courseLogo;
	}
	public void setCourseLogo(String courseLogo) {
		this.courseLogo = courseLogo;
	}
	public float getGarde() {
		return garde;
	}
	public void setGarde(float garde) {
		this.garde = garde;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	
	
}

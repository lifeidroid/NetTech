package com.lifeidroid.schooltech.Mdl;

public class Mdl_School {
	private String schoolName;
	private int schoolId;
	private String schoollogo;
	private int courseNum;
	public Mdl_School(String schoolName, int schoolId, String schoollogo,
			int courseNum) {
		super();
		this.schoolName = schoolName;
		this.schoolId = schoolId;
		this.schoollogo = schoollogo;
		this.courseNum = courseNum;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoollogo() {
		return schoollogo;
	}
	public void setSchoollogo(String schoollogo) {
		this.schoollogo = schoollogo;
	}
	public int getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
}

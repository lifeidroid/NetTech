package com.lifeidroid.schooltech.Mdl;



public class Mdl_Dept{
	private int deptId;
	private String deptname;
	private int coursenum;
	public Mdl_Dept(int deptId, String deptname, int coursenum) {
		super();
		this.deptId = deptId;
		this.deptname = deptname;
		this.coursenum = coursenum;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public int getCoursenum() {
		return coursenum;
	}
	public void setCoursenum(int coursenum) {
		this.coursenum = coursenum;
	}
	
}
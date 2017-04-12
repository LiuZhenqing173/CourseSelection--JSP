package com.mountain.domain;

import java.util.Date;

/**
 * student对象的封装
 * @author Administrator
 *
 */
public class Student {
	
	private String StuId;
	private String MajorId;
	private String StuName;
	private String Sex;
	private Date Birthday;
	private String password;
	private String MajorName;
	private String DepartmentId;
	private String DepartmentName;
	
	
	public String getStuId() {
		return StuId;
	}
	public void setStuId(String stuId) {
		StuId = stuId;
	}
	
	public String getStuName() {
		return StuName;
	}
	public void setStuName(String stuName) {
		StuName = stuName;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMajorId() {
		return MajorId;
	}
	public void setMajorId(String majorId) {
		MajorId = majorId;
	}
	//无参数的构造函数
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String stuId, String majorId, String stuName, String sex,
			Date birthday, String password, String majorName,
			String departmentId, String departmentName) {
		super();
		StuId = stuId;
		MajorId = majorId;
		StuName = stuName;
		Sex = sex;
		Birthday = birthday;
		this.password = password;
		MajorName = majorName;
		DepartmentId = departmentId;
		DepartmentName = departmentName;
	}
	public String getMajorName() {
		return MajorName;
	}
	public void setMajorName(String majorName) {
		MajorName = majorName;
	}
	public String getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	@Override
	public String toString() {
		return "Student [StuId=" + StuId + ", MajorId=" + MajorId
				+ ", StuName=" + StuName + ", Sex=" + Sex + ", Birthday="
				+ Birthday + ", password=" + password + ", MajorName="
				+ MajorName + ", DepartmentId=" + DepartmentId
				+ ", DepartmentName=" + DepartmentName + "]";
	}
	

	
	
	
	
	
	
	
}

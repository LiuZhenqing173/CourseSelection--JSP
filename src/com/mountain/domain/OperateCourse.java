package com.mountain.domain;
/**
 * 选课的JavaBean
 * @author Administrator
 *
 */
public class OperateCourse {

	//开始的学期
	private String StartTime;
	//课程号
	private String CourseId;
	//课程名
	private String CourseName;
	//学分
	private double Credit;
	//老师id
	private String TeacherId;
	//老师
	private String TeacherName;
	//学时
	private String StudyTime;
	//课程介绍
	private String Introduce;
	
	
	public String getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}
	@Override
	public String toString() {
		return "SelectCourse [StartTime=" + StartTime + ", CourseId="
				+ CourseId + ", CourseName=" + CourseName + ", Credit="
				+ Credit + ", TeacherId=" + TeacherId + ", TeacherName="
				+ TeacherName + ", StudyTime=" + StudyTime + ", Introduce="
				+ Introduce + "]";
	}
	public OperateCourse(String startTime, String courseId, String courseName,
			double credit, String teacherId, String teacherName,
			String studyTime, String introduce) {
		super();
		StartTime = startTime;
		CourseId = courseId;
		CourseName = courseName;
		Credit = credit;
		TeacherId = teacherId;
		TeacherName = teacherName;
		StudyTime = studyTime;
		Introduce = introduce;
	}
	public OperateCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getCourseId() {
		return CourseId;
	}
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public double getCredit() {
		return Credit;
	}
	public void setCredit(double credit) {
		Credit = credit;
	}
	public String getTeacherName() {
		return TeacherName;
	}
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}
	public String getStudyTime() {
		return StudyTime;
	}
	public void setStudyTime(String studyTime) {
		StudyTime = studyTime;
	}
	public String getIntroduce() {
		return Introduce;
	}
	public void setIntroduce(String introduce) {
		Introduce = introduce;
	}
}

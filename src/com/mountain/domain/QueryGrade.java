package com.mountain.domain;
/**
 * 查询成绩的JavaBean
 * 
 * jsp+servlet+javabean多表联合查询实现
 * 	方法1：查询结果是多表的字段，
 * 		你可以在创建模型层的时候根据查询的结果来创建，而不必要根据每个表的字段来创建。
 *  方法2：
 *  你在数据库中是一个一个表的，但是你在建立对象类的时候不能只是照搬表与表之间的主外键，
 *  	而是要把那个关联表对象当做另一个表的属性，
 *       这样查询的时候保存的就是那个对象了，里面的值就可以随意取了
 * @author Administrator
 *
 */
public class QueryGrade {
	
	//开始的学期
	private String StartTime;
	//课程号
	private String CourseId;
	//课程名
	private String CourseName;
	//学分
	private double Credit;
	//成绩
	private int Grade;
	//院系名称
	private String DepartmentName;
	//老师
	private String TeacherName;
	//学时
	private String StudyTime;
	//总分
	private double Total;
	//开始上课的时间
	private String ClassTime;
	//校区
	private String SchoolAddress;
	
	
	
	
	@Override
	public String toString() {
		return "QueryGrade [StartTime=" + StartTime + ", CourseId=" + CourseId
				+ ", CourseName=" + CourseName + ", Credit=" + Credit
				+ ", Grade=" + Grade + ", DepartmentName=" + DepartmentName
				+ ", TeacherName=" + TeacherName + ", StudyTime=" + StudyTime
				+ ", Total=" + Total + ", ClassTime=" + ClassTime
				+ ", SchoolAddress=" + SchoolAddress + "]";
	}
	public String getClassTime() {
		return ClassTime;
	}
	public void setClassTime(String classTime) {
		ClassTime = classTime;
	}
	public String getSchoolAddress() {
		return SchoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		SchoolAddress = schoolAddress;
	}
	public double getTotal() {
		return Total;
	}
	public void setTotal(double total) {
		Total = total;
	}
	public QueryGrade(String startTime, String courseId, String courseName,
			double credit, int grade, String departmentName,
			String teacherName, String studyTime, double total,
			String classTime, String schoolAddress) {
		super();
		StartTime = startTime;
		CourseId = courseId;
		CourseName = courseName;
		Credit = credit;
		Grade = grade;
		DepartmentName = departmentName;
		TeacherName = teacherName;
		StudyTime = studyTime;
		Total = total;
		ClassTime = classTime;
		SchoolAddress = schoolAddress;
	}
	public String getStudyTime() {
		return StudyTime;
	}
	public void setStudyTime(String studyTime) {
		StudyTime = studyTime;
	}
	public String getTeacherName() {
		return TeacherName;
	}
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}
	public QueryGrade() {
		super();
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
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
}

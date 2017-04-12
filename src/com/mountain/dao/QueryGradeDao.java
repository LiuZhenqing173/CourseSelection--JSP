package com.mountain.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.mountain.domain.QueryGrade;
import com.mountain.utils.MyJdbcUtils;

/**
 * 查询成绩的持久层
 * @author Administrator
 *
 */
public class QueryGradeDao {

	/**
	 * 根据学期在数据库中查询
	 * @return QueryGrade
	 * @throws SQLException 
	 */
	public List<QueryGrade> queryGradeByItem(String username) throws SQLException {
		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());
		String sql = 
		 "SELECT student.StuName, course.CourseName, course.Credit, course.StartTime, stu_course_teacher.Grade, course.CourseId, department.DepartmentName FROM courseselection.stu_course_teacher INNER JOIN courseselection.student  ON (stu_course_teacher.StuId = student.StuId) INNER JOIN courseselection.course  ON (stu_course_teacher.CourseId = course.CourseId) INNER JOIN courseselection.department ON (student.DepartmentId = department.DepartmentId) WHERE student.StuId=? ORDER BY StartTime ";
		List<QueryGrade> list =  runner.query(sql, new BeanListHandler<QueryGrade>(QueryGrade.class),username);
		
			return list;
	}
		
	/**
	 * 没有条件的查询   
	 *    查询的字段有 ---课程名，课程的学分，开始的学期，分数，课程id，学院名
	 * @param stuId
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public List<QueryGrade> queryGrade(String stuId) throws SQLException  {

		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());
		String sql = 
				 "SELECT  course.CourseName, course.Credit, course.StartTime, stu_course_teacher.Grade, course.CourseId, department.DepartmentName FROM courseselection.stu_course_teacher INNER JOIN courseselection.student  ON (stu_course_teacher.StuId = student.StuId) INNER JOIN courseselection.course  ON (stu_course_teacher.CourseId = course.CourseId) INNER JOIN courseselection.department ON (student.DepartmentId = department.DepartmentId) WHERE student.StuId=?";
		List<QueryGrade> list =  runner.query(sql, new BeanListHandler<QueryGrade>(QueryGrade.class),stuId);
		
			return list;
	}
	/**
	 * 根据下拉表单中的学期来查询
	 * @param stuId
	 * @param item
	 * @return List<QueryGrade>
	 * @throws Exception 
	 */
	public List<QueryGrade> queryGradeUseItem(String stuId, String item) throws Exception {
		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());
		/*String sql = 
		 "SELECT student.StuName, course.CourseName,teacher.TeacherName,course.StartTime,course.Credit, course.StartTime, stu_course_teacher.Grade, course.CourseId, department.DepartmentName FROM courseselection.stu_course_teacher INNER JOIN courseselection.student  ON (stu_course_teacher.StuId = student.StuId) INNER JOIN courseselection.course  ON (stu_course_teacher.CourseId = course.CourseId) INNER JOIN courseselection.department ON (student.DepartmentId = department.DepartmentId) WHERE student.StuId=? and StartTime = ? ";*/
		String sql = 
		"SELECT stu_course_teacher.CourseId, course.CourseName, course.Credit, course.StudyTime, course.StartTime,course.ClassTime, teacher.TeacherName, student.StuName, department.DepartmentName, course.StartTime, stu_course_teacher.Grade FROM courseselection.stu_course_teacher INNER JOIN courseselection.course  ON (stu_course_teacher.CourseId = course.CourseId) INNER JOIN courseselection.teacher  ON (stu_course_teacher.TeacherId = teacher.TeacherId) INNER JOIN courseselection.major  ON (teacher.MajorId = major.MajorId) INNER JOIN courseselection.student  ON (stu_course_teacher.StuId = student.StuId) INNER JOIN courseselection.department  ON (major.DepartmentId = department.DepartmentId) WHERE student.StuId=? and StartTime = ?";
		List<QueryGrade> list =  runner.query(sql, new BeanListHandler<QueryGrade>(QueryGrade.class),stuId,item);
		
		return list;
	}
	
	/**
	 * 查询已经选过的课程-----其中没有成绩字段
	 * @param stuId
	 * @return List<QueryGrade
	 * @throws Exception 
	 */
	public List<QueryGrade> querySelectedCourse(String stuId) throws Exception {
		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());     
		//查询的是QueryGrade JavaBean
		String sql = 
		 "SELECT stu_course_teacher.CourseId, course.CourseName,course.ClassTime, course.Credit, course.StudyTime, course.StartTime, teacher.TeacherName, student.StuName, department.DepartmentName FROM courseselection.stu_course_teacher INNER JOIN courseselection.course  ON (stu_course_teacher.CourseId = course.CourseId) INNER JOIN courseselection.teacher  ON (stu_course_teacher.TeacherId = teacher.TeacherId)INNER JOIN courseselection.major  ON (teacher.MajorId = major.MajorId)INNER JOIN courseselection.student  ON (stu_course_teacher.StuId = student.StuId)INNER JOIN courseselection.department  ON (major.DepartmentId = department.DepartmentId) WHERE student.StuId=?";
		List<QueryGrade> list = runner.query(sql, new BeanListHandler<QueryGrade>(QueryGrade.class), stuId);
		
		return list;
 }
	
	
	/**
	 * 查询还没有选的课程
	 * @param stuId
	 * @return List<QueryGrade>
	 * @throws SQLException 
	 */
	public List<QueryGrade> querryShouldSelectCourse(String stuId,String stuId1) throws SQLException {
		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());   
		String sql = "select  course.StartTime, course.CourseId, course.CourseName, course.Credit, course.StudyTime "
				+ "from course "
				+ "where  course.CourseId NOt in"
				+ "(select stu_course_teacher.CourseId "
				+ "from stu_course_teacher where StuID=? ) "
				+ "and course.MajorId = "
				+ "( select student.MajorId from student where StuId=?)"
				+ " ORDER BY StartTime";
		  
		//查询的是QueryGrade JavaBean
		List<QueryGrade> list = runner.query(sql, new BeanListHandler<QueryGrade>(QueryGrade.class), stuId,stuId);
		
		return list;
	}
	
	/**
	 * 通过下拉菜单查看可以选的课程
	 * @param stuId
	 * @param stuId2
	 * @param item
	 * @return
	 * @throws Exception 
	 */
	public List<QueryGrade> querryShouldSelectCourseByItem(String stuId,
			String stuId2, String item) throws Exception {
		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());   
		String sql = "select  course.StartTime, course.CourseId, course.CourseName, course.Credit, course.StudyTime "
				+ "from course "
				+ "where  course.CourseId NOt in"
				+ "(select stu_course_teacher.CourseId "
				+ "from stu_course_teacher where StuID=? ) "
				+ "and course.MajorId = "
				+ "( select student.MajorId from student where StuId=?)"
				+ "and course.StartTime=?";
		  
		//查询的是QueryGrade JavaBean
		List<QueryGrade> list = runner.query(sql, new BeanListHandler<QueryGrade>(QueryGrade.class), stuId,stuId,item);
		
		return list;
	}
	@Test 
	public void test() throws Exception{
		List<QueryGrade> list =  this.querySelectedCourse("131425020116");
		for (QueryGrade queryGrade : list) {
			System.out.println(queryGrade);
			//System.out.println(queryGrade.getTotal());
		}
	}
}

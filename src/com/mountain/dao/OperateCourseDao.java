package com.mountain.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.mountain.domain.OperateCourse;
import com.mountain.utils.MyJdbcUtils;

/**
 * 选课,退选的数据层
 * @author Administrator
 *
 */
public class OperateCourseDao {

	/**
	 * 选课的子过程
	 * @param courseId
	 * @return List<SelectCourse>
	 * @throws Exception 
	 */
	public List<OperateCourse> subSelectCourse(String courseId) throws Exception {
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());
		String sql = "SELECT course.CourseId, course.CourseName, course.StudyTime, course.Credit, teacher.TeacherName,teacher.TeacherId, course.Introduce FROM teacher_course,course,teacher where teacher_course.CourseId = course.CourseId and teacher_course.TeacherId = teacher.TeacherId and course.CourseId=?";
		List<OperateCourse> list = runner.query(sql, new BeanListHandler<OperateCourse>(OperateCourse.class), courseId);
		return list; 
	}
	/**
	 *  进行选课-----向数据库中添加数据(学生id，老师id，课程id)
	 * @param stuId	学号	
	 * @param courseId  课程编号
	 * @param teacherId 教师编号
	 * @return
	 * @throws SQLException 
	 */
	public int selectCource(String stuId, String courseId,String teacherId) throws SQLException {
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());
		String sql = "insert into stu_course_teacher(StuId,CourseId,TeacherId) values(?,?,?)";
		int i = runner.update(sql, stuId,courseId,teacherId);
		return i;
	}

	
	@Test
	public void test() throws Exception{
		int i = this.selectCource("131425020116", "0113", "0210");
		System.out.println(i);
	}
	/**
	 * 处理退选操作-----即删除所选的学号
	 * @param ids
	 * @throws Exception 
	 */
	public void deselectCourse(String[] ids) throws Exception {
		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());
		//编写sql数据
		String sql = "delete from stu_course_teacher where CourseId = ?";
		
		//把一位数组，转换为二维数组
		  //[[]],[[]]  1就是二维数组中只有一个元素
		Object[][] params = new Object[ids.length][1];
		for (int i = 0; i < ids.length; i++) {
			params[i][0] = ids[i];
		}
		
		//调用删除的方法（批处理）二维数组
		runner.batch(sql, params);
	}
}

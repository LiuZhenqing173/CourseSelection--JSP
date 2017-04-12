package com.mountain.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.mountain.dao.OperateCourseDao;
import com.mountain.domain.OperateCourse;

/**
 * 
 * 选课,退选的业务逻辑成层
 * @author Administrator
 *
 */
public class OperateCourseService {

	//选课的持久层
	private OperateCourseDao dao = new OperateCourseDao();
	/**
	 * 选课的子过程
	 * @param courseId
	 * @return  List<SelectCourse>
	 * @throws Exception 
	 */
	public List<OperateCourse> subSelectCourse(String courseId) throws Exception {
		 List<OperateCourse> list = dao.subSelectCourse(courseId);
		return list;
	}
	@Test
	public void test() throws Exception{
		List<OperateCourse> list = this.subSelectCourse("0109");
		System.out.println(list);
	}
	
	
	
	/**
	 * 进行选课-----向数据库中添加数据(学生id，课程id老师id，)
	 * @param stuId 	   学号
	 * @param courseId   课程编号
	 * @param teacherId  教师编号
	 * @throws SQLException 
	 */
	public int selectCource(String stuId, String courseId, String teacherId) throws SQLException {
		 int i = dao.selectCource(stuId,courseId,teacherId);
		 return i;
	}
	
	/**
	 * 处理退选操作-----即删除所选的学号
	 * @param ids
	 * @throws Exception 
	 */
	public void deselectCourse(String[] ids) throws Exception {
		dao.deselectCourse(ids); 
	}

}

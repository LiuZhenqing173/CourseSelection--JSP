package com.mountain.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import com.mountain.domain.Student;
import com.mountain.utils.MyJdbcUtils;

/**
 * 用户处理的持久层（数据层）
 * @author Administrator
 *
 */
public class UserDao {

	/**
	 * 用户登陆的实现
	 * 		只需要根据用户名在数据库中查找，而判断有没有找到则在业务层
	 * @param username
	 * @return 
	 * @throws SQLException 
	 */
	public Student login(String username) throws SQLException {
		//使用DBUtils保存数据
		QueryRunner runner = new QueryRunner(MyJdbcUtils.getDataSource());
		String sql = "SELECT student.StuId, student.MajorId, student.StuName, student.Sex, student.Birthday, student.Password, major.MajorName, department.DepartmentId, department.DepartmentName FROM courseselection.major INNER JOIN courseselection.department ON (major.DepartmentId = department.DepartmentId) INNER JOIN courseselection.student ON (student.DepartmentId = department.DepartmentId) AND (student.MajorId = major.MajorId) where StuId = ?";
		Student stu = runner.query(sql, new BeanHandler<Student>(Student.class),username );
		return  stu;
	}
	@Test
	public void test() throws SQLException{
		Student stu = this.login("131425020116");
		System.out.println(stu);
	}
}

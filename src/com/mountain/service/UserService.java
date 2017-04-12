package com.mountain.service;

import java.sql.SQLException;

import com.mountain.dao.UserDao;
import com.mountain.domain.Student;

/**
 * 用户业务层的实现
 * @author Administrator
 *
 */
public class UserService {

	/**
	 * 实现用户的登陆操作
	 * @param username
	 * @param password
	 * @return Student对象
	 * @throws SQLException 
	 */
	public Student login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		Student stu = dao.login(username);
		
		//判断stu是否查到,并且与传过来的密码进行对比
		if (stu != null && stu.getPassword().equals(password)) {
			return stu;
		} 
		return null;
		
	}

}

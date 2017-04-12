package com.mountain.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.mountain.dao.QueryGradeDao;
import com.mountain.domain.QueryGrade;

/**
 * 查询成绩的业务层
 * @author Administrator
 *
 */
public class OperateGradeService {

	//调用持久层、
	private QueryGradeDao dao = new QueryGradeDao() ;
			
	/**
	 * 根据学期查询成绩
	 * @return QueryGrade
	 * @throws SQLException 
	 */
	public List<QueryGrade> queryGradeByItem(String username) throws SQLException{
		
		
		return dao.queryGradeByItem(username);
	}

	/**
	 * 没有条件的查询成绩
	 * @param stuId
	 * @return List<QueryGrade>
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public List<QueryGrade> queryGrade(String stuId) throws SQLException  {
		return dao.queryGrade(stuId);
	}
	/**
	 * 根据下拉表单中的学期来查询
	 * @param stuId
	 * @param item
	 * @return List<QueryGrade>
	 * @throws Exception 
	 */
	public List<QueryGrade> queryGradeUseItem(String stuId, String item) throws Exception {
		return dao.queryGradeUseItem(stuId,item);
	}
	
	@Test
	public void test() throws Exception{
		List<QueryGrade> list = this.queryGradeUseItem("131425020116","1");
		for (QueryGrade queryGrade : list) {
			System.out.println(queryGrade);
		}
	}
	
	
	

	/**
	 * 查询已经选过的课程
	 * @param stuId
	 * @return
	 * @throws Exception 
	 */
	public List<QueryGrade> querySelectedCourse(String stuId) throws Exception {
		return dao.querySelectedCourse(stuId);
	}

/*	*//**
	 * 根据下拉菜单中的学期来进行查询
	 * @param stuId
	 * @param item
	 * @return
	 * @throws Exception 
	 *//*
	public List<QueryGrade> querySelectedCourseByItem(String stuId, String item) throws Exception {
		
		return  dao.queryGradeUseItem(stuId, item);
	}*/
	

	/**
	 * 查询还没有选的课程
	 * @param stuId
	 * @param stuId1
	 * @return List<QueryGrade>
	 * @throws SQLException 
	 */
public List<QueryGrade> querryShouldSelectCourse(String stuId, String stuId1) throws SQLException {
	
	return dao.querryShouldSelectCourse(stuId, stuId1);
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
		return dao.querryShouldSelectCourseByItem(stuId,stuId2,item);
	}



}

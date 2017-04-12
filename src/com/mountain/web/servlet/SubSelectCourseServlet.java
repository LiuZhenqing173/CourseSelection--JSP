package com.mountain.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mountain.domain.Student;
import com.mountain.service.OperateCourseService;

public class SubSelectCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//0.设置文字的编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//拿到学号
		String  courseId =request.getParameter("courseId");
		//拿到老师的id
		String teacherId = request.getParameter("teacherId");
		//取得登陆的学生对象
		Student stu = (Student) request.getSession().getAttribute("stu");
		//取得学号
		String stuId = stu.getStuId();
		//运用业务层
		OperateCourseService sc = new OperateCourseService();
		try {
			int i = sc.selectCource(stuId,courseId,teacherId);
			if(i == 0){
				//没有成功添加
				response.getWriter().write("真笨啊！还没选成功！");
				response.setHeader("Refresh", "2,URL="+request.getContextPath()+"/selectCourseServlet?op=beforehand");
				
			}else{
				//成功添加
				response.getWriter().write("成功的选课了");
				response.setHeader("Refresh", "2,URL="+request.getContextPath()+"/selectCourseServlet?op=beforehand");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		 

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}

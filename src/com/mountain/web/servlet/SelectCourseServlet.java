package com.mountain.web.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mountain.domain.OperateCourse;
import com.mountain.domain.QueryGrade;
import com.mountain.domain.Student;
import com.mountain.service.OperateCourseService;
/**
 * 进行选课功能的实现
 * 	*预选课程
 *     子页面的选课页面
 * 	*删除选课
 * 	*补选课程
 * @author Administrator
 *
 */
public class SelectCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取op参数---存在于left.jsp
		String op = request.getParameter("op");
		//获取opq参数---存在于BeforhandSelectCourse.jsp
		String opq = request.getParameter("opq");
		//进行参数的判断，然后在重写方法
			//一点击把所有的可以选的课程显示出来
		if("beforehand".equals(op)){
			//点击预选课程调用的方法
			 QueryCourseServlet.queryShouldSelectCourse(request, response, "/frontstage/BeforehandSelectCouce.jsp");
		}else if("beforehand".equals(opq)){
			/*6.JSP下拉框使用onchange事件提交表单到本文件后，会有刷新，怎么保持下拉框的选中状态为刚才
			 选择的下拉框选项？*/
			String p= request.getParameter("pp");
			request.setAttribute("qq", p);
			//点击下拉菜单处理的方法
			QueryCourseServlet.queryShouldSelectCourseByItem(request, response, "/frontstage/BeforehandSelectCouce.jsp", "pp");
		}else if("subSelect".equals(opq)){
			//选课的子过程
			subSelectCourse(request, response);
		}
	}



	/**
	 * 选课的子过程
	 * @param request
	 * @param response
	 */
	private void subSelectCourse(HttpServletRequest request,
			HttpServletResponse response) {
		//获取数据----获取需要进行处理子过程的CourseId
		String  courseId = request.getParameter("courseId");
		//调用业务逻辑方法
		OperateCourseService sc = new OperateCourseService();
		try {
			List<OperateCourse> cList = sc.subSelectCourse(courseId);
			//将list装入域中
			request.setAttribute("cList", cList);
	                 
	          
	        //在利用转发
			request.getRequestDispatcher("frontstage/SelsctCourse.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * 控制时间冲突的办法：
 *  前提拿到的是要进行选课的课程id，再根据这个课程id得到该课程的上课时间，
 *  最后根据上课的时间在数据库的学生选课表的查询课程name在选过的课程是否存在
 */


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}

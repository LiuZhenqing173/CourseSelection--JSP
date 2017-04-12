package com.mountain.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mountain.domain.QueryGrade;
import com.mountain.domain.Student;
import com.mountain.service.OperateGradeService;
/**
 * 学生课表
 * @author Administrator
 *
 */
public class CourseListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//显示学生第一学期的课表
		//0.设置文字的编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		/*//先拿到学生已经选过的对象
		String str = "/frontstage/CourseList.jsp";
		String p= request.getParameter("pp");
		request.setAttribute("qq", p);
		//------------放在了mList中了
		QueryCourseServlet.querySelectedCourseByItem(request, response, str, "pp");*/
		
		//调用业务代码，查询学生已经选过的课程，先显示第一学期的，直接写死
		OperateGradeService og = new OperateGradeService();
		Student stu = (Student) request.getSession().getAttribute("stu");
		try {
			List<QueryGrade> dList = og.queryGradeUseItem(stu.getStuId(), "1");
			//将获取到的list集合放到域中
			request.setAttribute("dList",dList);
			//在利用转发
			request.getRequestDispatcher("/frontstage/CourseList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}
}

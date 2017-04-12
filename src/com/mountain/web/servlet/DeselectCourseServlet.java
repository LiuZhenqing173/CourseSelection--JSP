package com.mountain.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mountain.service.OperateCourseService;

public class DeselectCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参座参数op---left.jsp
		String op = request.getParameter("op");
		String opq = request.getParameter("opq");
		if ("deselect".equals(op)) {
			//展示出该同学已经选过的课程
			QueryCourseServlet.querySelectedCourse(request, response,"/frontstage/DeselectCouse.jsp");
		} else if("deselect".equals(opq)){
			/*6.JSP下拉框使用onchange事件提交表单到本文件后，会有刷新，怎么保持下拉框的选中状态为刚才
			 选择的下拉框选项？*/
			String p= request.getParameter("pp");
			request.setAttribute("qq", p);
			//处理下拉菜单
			QueryCourseServlet.querySelectedCourseByItem(request, response, "/frontstage/DeselectCouse.jsp","pp");
		} else if("deleteSel".equals(opq)){
			//处理退选操作-----即删除所选的学号
			deselectCourse(request, response);
		}

	}

	/**
	 * 处理退选操作-----即删除所选的学号
	 * @param request
	 * @param response
	 */
	private void deselectCourse(HttpServletRequest request,
			HttpServletResponse response) {
		//获取所有的courseId
		String[] ids = request.getParameterValues("courseId");
		//采用批处理来进行删除操作
		OperateCourseService oc = new OperateCourseService();
		try {
			oc.deselectCourse(ids);
			//重新跳转到list.jsp页面
			//response.sendRedirect(request.getContextPath()+"/frontstage/DeselectCouse.jsp");
			response.sendRedirect(request.getContextPath()+"/deselectCourseServlet?op=deselect");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}

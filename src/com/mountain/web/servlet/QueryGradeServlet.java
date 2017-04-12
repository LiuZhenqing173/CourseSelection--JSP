package com.mountain.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mountain.domain.QueryGrade;
import com.mountain.domain.Student;
import com.mountain.service.OperateGradeService;
/**
 * 用于查询各种成绩的Servlet程序
 *    op=byItem为按学期查询，并由学期号进行排序
 * @author Administrator
 *
 */
public class QueryGradeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//处理查询的操作
		//0.设置文字的编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		
		//1.可以引入一个操作类型的参数
		String op = request.getParameter("op");
		String opc = request.getParameter("opc");
		if ("queryGrade".equals(opc)) {
			try {
				queryGrade(request,response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		//按学期排序来查询成绩
		}else if ("byItem".equals(op)) {
			try {
				queryByItem(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		//没有排序的查询成绩
		}else if ("all".equals(op)) {
				try {
				queryGrade(request,response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		//查询成绩用下拉菜单
		}else if("select".equals(op)){
				try {
					queryGradeUseItem(request,response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
}

	/**
	 * 根据下拉菜单的值进行查询
	 * @param request
	 * @param response
	 * @param item
	 * @throws Exception 
	 */
	private void queryGradeUseItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String item = request.getParameter("sel");
		
		//2.调用业务代码，实现登陆操作
		OperateGradeService qs = new OperateGradeService();
		Student stu = (Student) request.getSession().getAttribute("stu");
		List<QueryGrade> pList = qs.queryGradeUseItem(stu.getStuId(), item);
		//将获取到的list集合放到域中
		request.setAttribute("pList",pList);
		//在利用转发
		request.getRequestDispatcher("/frontstage/QueryGrade.jsp").forward(request, response);
		
	}

	/**
	 * 没有条件的查询成绩
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	private void queryGrade(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//2.调用业务代码，实现登陆操作
		OperateGradeService qs = new OperateGradeService();
		Student stu = (Student) request.getSession().getAttribute("stu");
		List<QueryGrade> pList = qs.queryGrade(stu.getStuId());
		//将获取到的list集合放到域中
		request.setAttribute("pList",pList);
		//在利用转发
		request.getRequestDispatcher("/frontstage/QueryGrade.jsp").forward(request, response);
		
	}

	/**
	 * 处理按学期进行查询的成绩
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void queryByItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*0.获得out对象
		  1.获得参数，并封装成对象
		  2.调用业务代码，实现登陆操作
		  3.判断登陆是否成功
		  4.进一步判断是否要记住用户名
		 */
		//0.获得out对象
		//PrintWriter out = response.getWriter();
		//2.调用业务代码，实现登陆操作
		OperateGradeService qs = new OperateGradeService();
		Student stu = (Student) request.getSession().getAttribute("stu");
		List<QueryGrade> pList = qs.queryGradeByItem(stu.getStuId());
		//将获取到的list集合放到域中
		request.setAttribute("pList",pList);
		//在利用转发
		request.getRequestDispatcher("/frontstage/QueryGrade.jsp").forward(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

}
